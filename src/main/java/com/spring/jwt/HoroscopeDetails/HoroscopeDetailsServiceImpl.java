package com.spring.jwt.HoroscopeDetails;

import com.spring.jwt.dto.UserProfileDTO;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.exception.UserNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoroscopeDetailsServiceImpl implements HoroscopeDetailsService{

    private final HoroscopeDetailsRepository horoscopeDetailsRepository;

    private final HoroscopeMapper horoscopeMapper;

    @Override
    public HoroscopeDTO saveHoroscopeDetails(HoroscopeDTO horoscopeDTO) {

        if(horoscopeDTO==null){
            throw new IllegalArgumentException("Horoscope data cannot be null");
        }
        HoroscopeDetails entity = horoscopeMapper.toEntity(horoscopeDTO);
        HoroscopeDetails savedHoroscope = horoscopeDetailsRepository.save(entity);
        return horoscopeMapper.toDTO(savedHoroscope);

    }

    @Override
    public HoroscopeDTO getHoroscopeById(Integer id) {
        return horoscopeDetailsRepository.findById(id).map(horoscopeMapper::toDTO)
                .orElseThrow(()-> new HoroscopeNotFoundException("horoscope not found with id :"+ id));
    }

    @Override
    public HoroscopeDTO updateHoroscope(Integer id, HoroscopeDTO dto){
        HoroscopeDetails horoscope = horoscopeDetailsRepository.findById(id)
                .orElseThrow(() -> new HoroscopeNotFoundException("Horoscope not found"));

        if (horoscope.getBirthPlace() != null) {
            horoscope.setBirthPlace(dto.getBirthPlace());
        }
        if (horoscope.getDob() != null) {
            horoscope.setDob(dto.getDob());
        }
        if (horoscope.getTime() != null) {
            horoscope.setTime(dto.getTime());
        }
        if (horoscope.getRashi() != null) {
            horoscope.setRashi(dto.getRashi());
        }
        if (horoscope.getNakshatra() != null) {
            horoscope.setNakshatra(dto.getNakshatra());
        }
        if (horoscope.getCharan() != null) {
            horoscope.setCharan(dto.getCharan());
        }
        if (horoscope.getNadi() != null) {
            horoscope.setNadi(dto.getNadi());
        }
        if (horoscope.getGan() != null) {
            horoscope.setGan(dto.getGan());
        }
        if (horoscope.getMangal() != null) {
            horoscope.setMangal(dto.getMangal());
        }
        if (horoscope.getGotra() != null) {
            horoscope.setGotra(dto.getGotra());
        }
        if (horoscope.getDevak() != null) {
            horoscope.setDevak(dto.getDevak());
        }
        if (horoscope.getStatus1() != null) {
            horoscope.setStatus1(dto.getStatus());
        }
        if (horoscope.getNadi() != null) {
            horoscope.setNadi(dto.getNadi());
        }
        HoroscopeDetails savedHoroscope = horoscopeDetailsRepository.save(horoscope);
        return horoscopeMapper.toDTO(savedHoroscope);
    }

    @Override
    public void deleteHoroscope(Integer id) {
        horoscopeDetailsRepository.deleteById(id);

    }


}
