package com.spring.jwt.PartnerPreference;

import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
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
}
