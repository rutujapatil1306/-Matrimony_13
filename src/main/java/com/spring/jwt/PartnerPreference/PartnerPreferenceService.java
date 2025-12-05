package com.spring.jwt.PartnerPreference;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface PartnerPreferenceService {

    BaseResponseDTO create(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO);
    PartnerPreferenceDTO getByUserId(Integer userId);

    ApiResponse updatePreference(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO);
}
