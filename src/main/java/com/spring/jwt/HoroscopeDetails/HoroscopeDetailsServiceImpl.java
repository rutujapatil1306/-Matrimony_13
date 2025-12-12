package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.HoroscopeNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class HoroscopeDetailsServiceImpl implements HoroscopeDetailsService{

    private final HoroscopeDetailsRepository horoscopeDetailsRepository;
    private final UserRepository userRepository;
    private final HoroscopeMapper horoscopeMapper;
    private final CompleteProfileRepository repository;

    @Override
    @Transactional
    public BaseResponseDTO saveHoroscopeDetails(Integer userId, HoroscopeDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        HoroscopeDetails entity = horoscopeMapper.toEntity(dto);
        entity.setUser(user);

        horoscopeDetailsRepository.save(entity);

        CompleteProfile completeProfile = repository.findByUserId(userId);
        completeProfile.setHoroscopeDetails(entity);
        repository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Horoscope saved successfully");
        response.setID(entity.getHoroscopeDetailsId());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public HoroscopeDTO getHoroscopeById(Integer userId) {
        return horoscopeDetailsRepository.findByUserId(userId).map(horoscopeMapper::toDTO)
                .orElseThrow(()-> new HoroscopeNotFoundException("horoscope not found with id :"+ userId));
    }

    @Override
    @Transactional
    public ApiResponse updateHoroscope(Integer userId, HoroscopeDTO dto){
        HoroscopeDetails horoscope = horoscopeDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new HoroscopeNotFoundException("Horoscope not found"));

        HelperUtil.getDataIfNotNull(dto::getBirthPlace, horoscope::setBirthPlace);
        HelperUtil.getDataIfNotNull(dto::getCharan, horoscope::setCharan);
        HelperUtil.getDataIfNotNull(dto::getGan, horoscope::setGan);
        HelperUtil.getDataIfNotNull(dto::getGotra, horoscope::setGotra);
        HelperUtil.getDataIfNotNull(dto::getDevak, horoscope::setDevak);
        HelperUtil.getDataIfNotNull(dto::getMangal, horoscope::setMangal);
        HelperUtil.getDataIfNotNull(dto::getNadi, horoscope::setNadi);
        HelperUtil.getDataIfNotNull(dto::getNakshatra, horoscope::setNakshatra);
        HelperUtil.getDataIfNotNull(dto::getRashi, horoscope::setRashi);
        HelperUtil.getDataIfNotNull(dto::getTime, horoscope::setTime);
        HelperUtil.getDataIfNotNull(dto::getDob, horoscope::setDob);

        HoroscopeDetails savedHoroscope = horoscopeDetailsRepository.save(horoscope);
        HoroscopeDTO responseDTO = horoscopeMapper.toDTO(savedHoroscope);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Horoscope details updated successfully");
        response.setData(responseDTO);

        return response;
    }
//
//    @Override
//    public void deleteHoroscope(Integer userId) {
//        horoscopeDetailsRepository.deleteById(userId);

}
