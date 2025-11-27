package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.dto.UserProfileDTO;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.utils.BaseResponseDTO;

public interface HoroscopeDetailsService {

    BaseResponseDTO saveHoroscopeDetails(Integer userId, HoroscopeDTO horoscopeDTO);

    HoroscopeDTO getHoroscopeById(Integer id);

    HoroscopeDTO updateHoroscope(Integer id, HoroscopeDTO dto);
}
