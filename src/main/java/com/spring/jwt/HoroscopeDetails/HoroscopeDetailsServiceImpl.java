package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.HoroscopeNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HoroscopeDetailsServiceImpl implements HoroscopeDetailsService{

    private final HoroscopeDetailsRepository horoscopeDetailsRepository;
    private final UserRepository userRepository;
    private final HoroscopeMapper horoscopeMapper;


    @Override
    public BaseResponseDTO saveHoroscopeDetails(Integer userId, HoroscopeDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        HoroscopeDetails entity = horoscopeMapper.toEntity(dto);
        entity.setUser(user);

        horoscopeDetailsRepository.save(entity);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Horoscope saved successfully");
        response.setID(entity.getHoroscopeDetailsId());
        return response;
    }

    @Override
    public HoroscopeDTO getHoroscopeById(Integer userId) {
        return horoscopeDetailsRepository.findByUserId(userId).map(horoscopeMapper::toDTO)
                .orElseThrow(()-> new HoroscopeNotFoundException("horoscope not found with id :"+ userId));
    }

    @Override
    public HoroscopeDTO updateHoroscope(Integer userId, HoroscopeDTO dto){
        HoroscopeDetails horoscope = horoscopeDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new HoroscopeNotFoundException("Horoscope not found"));

        if (dto.getBirthPlace() != null) {
            horoscope.setBirthPlace(dto.getBirthPlace());
        }
        if (dto.getDob() != null) {
            horoscope.setDob(dto.getDob());
        }
        if (dto.getTime() != null) {
            horoscope.setTime(dto.getTime());
        }
        if (dto.getRashi() != null) {
            horoscope.setRashi(dto.getRashi());
        }
        if (dto.getNakshatra() != null) {
            horoscope.setNakshatra(dto.getNakshatra());
        }
        if (dto.getCharan() != null) {
            horoscope.setCharan(dto.getCharan());
        }
        if (dto.getNadi() != null) {
            horoscope.setNadi(dto.getNadi());
        }
        if (dto.getGan() != null) {
            horoscope.setGan(dto.getGan());
        }
        if (dto.getMangal() != null) {
            horoscope.setMangal(dto.getMangal());
        }
        if (dto.getGotra() != null) {
            horoscope.setGotra(dto.getGotra());
        }
        if (dto.getDevak() != null) {
            horoscope.setDevak(dto.getDevak());
        }
        if (dto.getNadi() != null) {
            horoscope.setNadi(dto.getNadi());
        }
        HoroscopeDetails savedHoroscope = horoscopeDetailsRepository.save(horoscope);
        return horoscopeMapper.toDTO(savedHoroscope);
    }
//
//    @Override
//    public void deleteHoroscope(Integer userId) {
//        horoscopeDetailsRepository.deleteById(userId);

}
