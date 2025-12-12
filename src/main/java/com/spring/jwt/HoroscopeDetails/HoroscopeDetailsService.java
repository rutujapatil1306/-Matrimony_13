package com.spring.jwt.HoroscopeDetails;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface HoroscopeDetailsService {

    BaseResponseDTO saveHoroscopeDetails(Integer userId, HoroscopeDTO horoscopeDTO);

    HoroscopeDTO getHoroscopeById(Integer userId);

    ApiResponse  updateHoroscope(Integer id, HoroscopeDTO dto);

    BaseResponseDTO deleteHoroscope(Integer userID);
}
