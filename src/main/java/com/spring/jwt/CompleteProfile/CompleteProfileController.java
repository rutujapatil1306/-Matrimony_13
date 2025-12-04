package com.spring.jwt.CompleteProfile;

import com.spring.jwt.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/completeProfile")
@RequiredArgsConstructor
public class CompleteProfileController {

    private final CompleteProfileService completeProfileService;

    @GetMapping("/get")
    public ResponseEntity<FullProfileDTO> getFullProfile() {

        Integer userId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(completeProfileService.getFullProfile(userId));
    }

}
