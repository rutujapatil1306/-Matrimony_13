package com.spring.jwt.PartnerPreference;

import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partnerPreference")
@RequiredArgsConstructor
public class PartnerPreferenceController {

    private final PartnerPreferenceService partnerPreferenceService;


    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> create(
            @RequestBody PartnerPreferenceDTO partnerPreferenceDTO) {


        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = partnerPreferenceService.create(userId, partnerPreferenceDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Fetching partner preference details using user id")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<PartnerPreferenceDTO>> getHoroscopeByID() {

        Integer userId = SecurityUtil.getCurrentUserId();
        PartnerPreferenceDTO preference = partnerPreferenceService.getByUserId(userId);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Partner preference Details For Id " + userId, preference));
    }


    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> updateByUserID(
            @RequestBody PartnerPreferenceDTO partnerPreferenceDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        partnerPreferenceService.updatePreference(userId, partnerPreferenceDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Partner preference Updated Successfully !"));
    }
}
