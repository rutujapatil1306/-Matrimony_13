package com.spring.jwt.profile;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Validated
@Tag(name = "Profile Api", description = "Api for profile management")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/createProfile")
    public ResponseEntity<BaseResponseDTO> createProfile(
            @RequestBody ProfileDTO profileDTO) {

        Integer userId= SecurityUtil.getCurrentUserId();

        System.out.println("🔍 Extracted from JWT token: userId = " + userId);
        BaseResponseDTO response = profileService.createProfile(userId,profileDTO);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
      }

    @PatchMapping("/updateProfile")
    public ResponseEntity<ApiResponse<ProfileDTO>> updateByUserID(
            @RequestBody ProfileDTO dto){

        Integer userId = SecurityUtil.getCurrentUserId();
        profileService.updateProfile(userId, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Profile Updated Successfully !"));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<ApiResponse<ProfileDTO>> getProfileById(){

        Integer userId = SecurityUtil.getCurrentUserId();
        ProfileDTO response= profileService.getProfile(userId);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Profile details for user id :"+ userId, response));
    }

}

