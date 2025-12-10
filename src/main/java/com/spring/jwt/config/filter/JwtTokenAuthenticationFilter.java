package com.spring.jwt.config.filter;


import com.spring.jwt.jwt.JwtConfig;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.jwt.ActiveSessionService;
import com.spring.jwt.service.security.UserDetailsCustom;
import com.spring.jwt.service.security.UserDetailsServiceCustom;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.HelperUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final JwtService jwtService;
    private final UserDetailsServiceCustom userDetailsService;
    private final RequestMatcher publicUrls;
    private final ActiveSessionService activeSessionService;

    private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";

    private boolean setauthreq = true;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
//        String path = request.getRequestURI();

        // PUBLIC URLs skip authentication
        if (isPublic(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // No token -> skip
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getJwtFromRequest(request);

        try {

            // Validate token
            if (!jwtService.isValidToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extract claims
            Claims claims = jwtService.extractClaims(token);
            Integer userId = claims.get("userId", Integer.class);
            String username = claims.getSubject();

            // Load user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create Authentication
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // Store userId so we can fetch it anywhere
            auth.setDetails(userId);

            // Set in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        // IMPORTANT: Only ONE doFilter call
        filterChain.doFilter(request, response);
    }


        private boolean isPublic(HttpServletRequest request) {
        String path = request.getRequestURI();
        // Minimal allowlist to avoid interfering with public endpoints
        if (path.equals(jwtConfig.getUrl()) || path.equals(jwtConfig.getRefreshUrl())) return true;
        if (path.startsWith("/api/auth/")) return true;
        if (path.startsWith("/api/public/")) return true;
        if (path.startsWith("/v2/api-docs") || path.startsWith("/v3/api-docs")) return true;
        if (path.startsWith("/swagger-ui") || path.equals("/swagger-ui.html")) return true;
        if (path.startsWith("/swagger-resources") || path.startsWith("/configuration/")) return true;
        if (path.startsWith("/webjars/")) return true;
        if (path.equals("/api/v1/users/register") || path.startsWith("/api/v1/users/password/")) return true;
        if (path.equals("/api/v1/papers/solutions/pdf")) return true;
        // Fallback to configured matcher if present
        try { return publicUrls != null && publicUrls.matches(request); } catch (Exception ignored) {}
        return false;
    }

    private String getSpecificInvalidReason(String token, HttpServletRequest request) {
        try {
            if (jwtService.isBlacklisted(token)) {
                return "Token is revoked/blacklisted";
            }
            Claims claims = jwtService.extractClaims(token);
            String tokenDfp = claims.get("dfp", String.class);
            String reqDfp = jwtService.generateDeviceFingerprint(request);
            if (StringUtils.hasText(tokenDfp) && StringUtils.hasText(reqDfp) && !tokenDfp.equals(reqDfp)) {
                return "Device mismatch: please login again on this device";
            }
            String username = claims.getSubject();
            String tokenId = claims.getId();
            if (StringUtils.hasText(username) && StringUtils.hasText(tokenId) && !activeSessionService.isCurrentAccessToken(username, tokenId)) {
                return "You are logged in on another device. Please logout from the other device to continue";
            }
            return "Invalid or expired token";
        } catch (ExpiredJwtException e) {
            return "Expired token";
        } catch (JwtException e) {
            return "Malformed or invalid token";
        } catch (Exception e) {
            return "Unauthorized";
        }
    }

    /**
     * Process the JWT token and set authentication if valid
     * @return true if token is valid and authentication was set, false otherwise
     */
    private boolean processToken(HttpServletRequest request, String token) {
        String deviceFingerprint = jwtService.generateDeviceFingerprint(request);
        if (jwtService.isValidToken(token, deviceFingerprint)) {
            Claims claims = jwtService.extractClaims(token);
            String username = claims.getSubject();

            if (jwtService.isRefreshToken(token)) {
                log.warn("Refresh token used for API access - not allowed");
                return false;
            }
            
            if (!ObjectUtils.isEmpty(username)) {
                log.debug("Valid token found for user: {}", username);

                List<String> authorities = claims.get("authorities", List.class);
                if (authorities == null) {
                    authorities = claims.get("roles", List.class);
                }
                
                if (authorities != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    );


                    SecurityContextHolder.getContext().setAuthentication(auth);
                    // If token is not the current session, fail fast with specific message
                    try {
                        String tokenId = claims.getId();
                        if (!activeSessionService.isCurrentAccessToken(username, tokenId)) {
                            log.warn("Token not current for user: {}", username);
                            return false;
                        }
                    } catch (Exception ignored) {}
                    log.debug("Authentication set in security context for user: {}", username);
                    return true;
                } else {
                    log.warn("No authorities found in token for user: {}", username);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Extract JWT token from request (header or cookie)
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtConfig.getPrefix() + " ")) {
            log.debug("Found token in Authorization header");
            return bearerToken.substring((jwtConfig.getPrefix() + " ").length());
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> accessTokenCookie = Arrays.stream(cookies)
                .filter(cookie -> ACCESS_TOKEN_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
                
            if (accessTokenCookie.isPresent()) {
                log.debug("Found access token in cookie");
                return accessTokenCookie.get().getValue();
            }
        }
        return null;
    }
    
    private void handleAccessBlocked(HttpServletResponse response) throws IOException {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
        responseDTO.setMessage("d7324asdx8hg");

        String json = HelperUtils.JSON_WRITER.writeValueAsString(responseDTO);

        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
    
    private void handleAccessDenied(HttpServletResponse response) throws IOException {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        responseDTO.setMessage("Access denied: Authentication required");

        String json = HelperUtils.JSON_WRITER.writeValueAsString(responseDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
    
    private void handleInvalidToken(HttpServletResponse response, String message) throws IOException {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        responseDTO.setMessage(message);

        String json = HelperUtils.JSON_WRITER.writeValueAsString(responseDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
    
    private void handleExpiredToken(HttpServletResponse response) throws IOException {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        responseDTO.setMessage("Expired token");

        String json = HelperUtils.JSON_WRITER.writeValueAsString(responseDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
    
    private void handleAuthenticationException(HttpServletResponse response, Exception e) throws IOException {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        responseDTO.setCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        responseDTO.setMessage("Authentication failed: " + e.getMessage());

        String json = HelperUtils.JSON_WRITER.writeValueAsString(responseDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
    }
    
    public void setauthreq(boolean setauthreq) {
        this.setauthreq = setauthreq;
    }
}
