package com.spring.jwt.profile;

import com.spring.jwt.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Api for profile creation")
    @PatchMapping("/createProfile")
    public ResponseEntity<ApiResponse<ProfileDTO>> createProfile(
            @RequestParam Integer userId,
            @RequestBody @Valid ProfileDTO profileDTO){
        try {
            profileService.createProfile(userId, profileDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("profile created successfully"));
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST,"profile creation failed",e.getMessage()));
        }

    }
}
