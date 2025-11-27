package com.spring.jwt.PartnerPreference;

import com.spring.jwt.utils.BaseResponseDTO;

public interface PartnerPreferenceService {

    BaseResponseDTO create(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO);
}
