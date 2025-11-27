package com.spring.jwt.utils;

import com.spring.jwt.entity.User;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final JwtService jwtService;


    public Integer extractUSerID(String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        if (!jwtService.isValidToken(token)) {
            throw new RuntimeException("Invalid token");
        }
        Claims claims = jwtService.extractClaims(token);
        return claims.get("userId", Integer.class);
    }

}
