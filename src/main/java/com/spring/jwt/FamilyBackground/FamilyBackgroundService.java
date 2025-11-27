package com.spring.jwt.FamilyBackground;

import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface FamilyBackgroundService {

    BaseResponseDTO saveFamilyBackground(Integer userId, FamilyBackgroundDTO dto);

    FamilyBackgroundDTO getBackground(Integer userId);
    ApiResponse updateBackground(Integer userId, FamilyBackgroundDTO dto);
}
