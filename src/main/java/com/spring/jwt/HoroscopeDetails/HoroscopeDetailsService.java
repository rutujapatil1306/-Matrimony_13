package com.spring.jwt.HoroscopeDetails;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface HoroscopeDetailsService {

    BaseResponseDTO saveHoroscopeDetails(Integer userId, HoroscopeDTO horoscopeDTO);

    HoroscopeDTO getHoroscopeById(Integer userId);

    ApiResponse  updateHoroscope(Integer id, HoroscopeDTO dto);
}
