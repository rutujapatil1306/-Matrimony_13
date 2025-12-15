package com.spring.jwt.CompleteProfile;

import com.spring.jwt.dto.DisplayProfileDTO;
import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.Enums.Gender;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complete")
@RequiredArgsConstructor
@Validated
public class CompleteProfileController {

    private final CompleteProfileService completeProfileService;

    @GetMapping("/getOwnProfile")
    public ResponseEntity <ApiResponse<FullProfileDTO>> getFullProfile() {

        Integer userId = SecurityUtil.getCurrentUserId();
        FullProfileDTO fullProfile = completeProfileService.getFullProfile(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Own profile details fetched successfully " , fullProfile));
    }

    @GetMapping("/getProfile/{userId}")
    public ResponseEntity <ApiResponse<FullProfileDTO>> getPublicProfile(@PathVariable Integer userId) {

        FullProfileDTO displayProfile = completeProfileService.getDisplayProfile(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("User Profile fetched successfully", displayProfile));
    }

    @GetMapping("/getProfileByGender")
    public ResponseEntity<ApiResponse<Page<PublicProfileDTO>>> getProfile(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 30, message = "Page size cannot exceed 30") int size,
            @RequestParam Gender gender) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("completeProfileId").descending());

        Page<PublicProfileDTO> profileByGender = completeProfileService.getProfileByGender(pageable, gender);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(gender +" "+"profiles fetched successfully", profileByGender));
    }
}
