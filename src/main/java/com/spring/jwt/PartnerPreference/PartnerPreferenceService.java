package com.spring.jwt.PartnerPreference;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface PartnerPreferenceService {

    BaseResponseDTO createPreference(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO);
    PartnerPreferenceDTO getPreference(Integer userId);
    ApiResponse updatePreference(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO);
}
