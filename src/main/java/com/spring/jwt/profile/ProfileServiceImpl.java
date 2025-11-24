package com.spring.jwt.profile;


import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProfileDTO createProfile(Integer userId, ProfileDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile existing = profileRepository.findByUserId(userId);
        if (existing != null) {
            throw new RuntimeException("Profile already exists");
        }
        UserProfile entity = new UserProfile();

        entity.setUser(user);
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

    @Override
    public ProfileDTO updateProfile(Integer userId, ProfileDTO dto) {
        UserProfile profile = profileRepository.findByUserId(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }
        if (dto.getFirstName() != null) {
            profile.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            profile.setLastName(dto.getLastName());
        }
        if (dto.getMiddleName() != null) {
            profile.setMiddleName(dto.getMiddleName());
        }
        if (dto.getAddress() != null) {
            profile.setAddress(dto.getAddress());
        }
        if (dto.getTaluka() != null) {
            profile.setTaluka(dto.getTaluka());
        }
        if (dto.getDistrict() != null) {
            profile.setDistrict(dto.getDistrict());
        }
        if (dto.getPinCode() != null) {
            profile.setPinCode(dto.getPinCode());
        }
        if (dto.getMobileNumber() != null) {
            profile.setMobileNumber(dto.getMobileNumber());
        }
        if (dto.getGender() != null) {
            profile.setGender(dto.getGender());
        }
        if (dto.getReligion() != null) {
            profile.setReligion(dto.getReligion());
        }
        if (dto.getCaste() != null) {
            profile.setCaste(dto.getCaste());
        }
        if (dto.getMaritalStatus() != null) {
            profile.setMaritalStatus(dto.getMaritalStatus());
        }
        if (dto.getHeight() != null) {
            profile.setHeight(dto.getHeight());
        }
        if (dto.getWeight() != null) {
            profile.setWeight(dto.getWeight());
        }
        if (dto.getBloodGroup() != null) {
            profile.setBloodGroup(dto.getBloodGroup());
        }
        if (dto.getComplexion() != null) {
            profile.setComplexion(dto.getComplexion());
        }
        if (dto.getDiet() != null) {
            profile.setDiet(dto.getDiet());
        }
        if (dto.getSpectacle() != null) {
            profile.setSpectacle(dto.getSpectacle());
        }
        if (dto.getLens() != null) {
            profile.setLens(dto.getLens());
        }
        if (dto.getPhysicallyChallenged() != null) {
            profile.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        }
        if (dto.getHomeTownDistrict() != null) {
            profile.setHomeTownDistrict(dto.getHomeTownDistrict());
        }
        if (dto.getNativeTaluka() != null) {
            profile.setNativeTaluka(dto.getNativeTaluka());
        }
        if (dto.getCurrentCity() != null) {
            profile.setCurrentCity(dto.getCurrentCity());
        }
        if (dto.getUserProfileCol() != null) {
            profile.setUserProfileCol(dto.getUserProfileCol());
        }

        UserProfile savedProfile = profileRepository.save(profile);
        return profileMapper.toDTO(savedProfile);

    }


    @Override
    public ProfileDTO getProfile(Integer userId) {
        UserProfile userProfile= profileRepository.findByUserId(userId);
        if(userProfile==null){
            throw new ProfileNotFoundException("profile not found");
        }
        return profileMapper.toDTO(userProfile);
    }

    @Override
    public void deleteProfile(Integer userId) {
        UserProfile byUserId = profileRepository.findByUserId(userId);
        profileRepository.delete(byUserId);
    }

}