package com.spring.jwt.PartnerPreference;

import com.spring.jwt.ContactDetails.ContactDTO;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/PartnerPreference")
@RequiredArgsConstructor
public class PartnerPreferenceController {

    private final PartnerPreferenceService partnerPreferenceService;
    private final JwtUtils jwtUtils;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody PartnerPreferenceDTO partnerPreferenceDTO) {

        Integer userId= jwtUtils.extractUSerID(authHeader);
        BaseResponseDTO response = partnerPreferenceService.create(partnerPreferenceDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
