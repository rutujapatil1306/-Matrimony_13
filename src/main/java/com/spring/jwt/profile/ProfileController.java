package com.spring.jwt.profile;

import com.spring.jwt.entity.User;
import com.spring.jwt.jwt.JwtService;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
@Tag(name = "Profile Api", description = "Api for profile management")
public class ProfileController {

    private final ProfileService profileService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    private Integer extractUserId(String authHeader) {
        String token = authHeader.substring(7);

        // 1. Validate token
        if (!jwtService.isValidToken(token)) {
            throw new RuntimeException("Invalid token");
        }
        // 2. Extract claims
        Claims claims = jwtService.extractClaims(token);
        // 3. Extract email (subject)
        String email = claims.getSubject();
        // 4. Fetch userId
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getId();
    }

    @Operation(summary = "Api for profile creation")
    @PostMapping ("/createProfile")
    public ResponseEntity<ApiResponse<ProfileDTO>> createProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid ProfileDTO profileDTO){
        try {
            Integer userId= extractUserId(authHeader);
            profileService.createProfile(userId, profileDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("profile created successfully"));
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST,"profile creation failed",e.getMessage()));
        }

    }
}
