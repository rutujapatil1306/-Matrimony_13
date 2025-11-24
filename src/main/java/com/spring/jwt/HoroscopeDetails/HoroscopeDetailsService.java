package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.dto.UserProfileDTO;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.User;

public interface HoroscopeDetailsService {

    HoroscopeDTO saveHoroscopeDetails(HoroscopeDTO horoscopeDTO);

    HoroscopeDTO getHoroscopeById(Integer id);

    HoroscopeDTO updateHoroscope(Integer id, HoroscopeDTO dto);

    void deleteHoroscope(Integer id);
}
