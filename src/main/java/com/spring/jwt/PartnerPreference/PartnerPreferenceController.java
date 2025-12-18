package com.spring.jwt.PartnerPreference;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/partnerPreference")
@RequiredArgsConstructor
public class PartnerPreferenceController {

    private final PartnerPreferenceService partnerPreferenceService;


    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> createPreference(
            @RequestBody @Valid PartnerPreferenceDTO partnerPreferenceDTO) {


        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = partnerPreferenceService.createPreference(userId, partnerPreferenceDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Fetching partner preference details using user id")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<PartnerPreferenceDTO>> getPreference() {

        Integer userId = SecurityUtil.getCurrentUserId();
        PartnerPreferenceDTO preference = partnerPreferenceService.getPreference(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Partner preference Details For userId " + userId, preference));
    }


    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<PartnerPreferenceDTO>> updatePreference(
            @RequestBody PartnerPreferenceDTO partnerPreferenceDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        partnerPreferenceService.updatePreference(userId, partnerPreferenceDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Partner preference Updated Successfully !"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseDTO> deletePreference(){

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = partnerPreferenceService.deletePreference(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
