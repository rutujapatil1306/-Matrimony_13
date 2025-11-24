package com.spring.jwt.profile;


import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.UserNotFoundExceptions;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    @Transactional
    public ProfileDTO createProfile(Integer userId, ProfileDTO dto) {

        UserProfile entity= profileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("user not found with id : " + userId));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setAddress(dto.getAddress());
        entity.setTaluka(dto.getTaluka());
        entity.setDistrict(dto.getDistrict());
        entity.setPinCode(dto.getPinCode());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setGender(dto.getGender());
        entity.setReligion(dto.getReligion());
        entity.setCaste(dto.getCaste());
        entity.setMaritalStatus(dto.getMaritalStatus());
        entity.setHeight(dto.getHeight());
        entity.setWeight(dto.getWeight());
        entity.setBloodGroup(dto.getBloodGroup());
        entity.setComplexion(dto.getComplexion());
        entity.setDiet(dto.getDiet());
        entity.setSpectacle(dto.getSpectacle());
        entity.setLens(dto.getLens());
        entity.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        entity.setHomeTownDistrict(dto.getHomeTownDistrict());
        entity.setNativeTaluka(dto.getNativeTaluka());
        entity.setCurrentCity(dto.getCurrentCity());

        UserProfile savedProfile = profileRepository.save(entity);
        return profileMapper.toDTO(savedProfile);

    }
}