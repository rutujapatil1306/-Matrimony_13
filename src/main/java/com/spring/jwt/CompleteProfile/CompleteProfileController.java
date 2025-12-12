package com.spring.jwt.CompleteProfile;

import com.spring.jwt.dto.DisplayProfileDTO;
import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.Enums.Gender;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complete")
@RequiredArgsConstructor
public class CompleteProfileController {

    private final CompleteProfileService completeProfileService;

    @GetMapping("/getOwnProfile")
    public ResponseEntity<FullProfileDTO> getFullProfile() {

        Integer userId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(completeProfileService.getFullProfile(userId));
    }

    @GetMapping("/getProfile/{userId}")
    public DisplayProfileDTO getPublicProfile(@PathVariable Integer userId) {
        return completeProfileService.getDisplayProfile(userId);
    }

    @GetMapping("/getProfileByGender")
    public Page<PublicProfileDTO> getProfile(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 30, message = "Page size cannot exceed 30") int size,
            @RequestParam Gender gender) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("completeProfileId").descending());

        return completeProfileService.getProfile(pageable, gender);
    }
}
