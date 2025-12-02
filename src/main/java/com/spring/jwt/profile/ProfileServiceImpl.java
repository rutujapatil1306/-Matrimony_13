package com.spring.jwt.profile;


import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.ProfileNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;
    private final CompleteProfileRepository repository;

    @Override
    @Transactional
    public BaseResponseDTO createProfile(Integer userId, ProfileDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        UserProfile entity = profileMapper.toEntity(dto);
        entity.setUser(user);
        profileRepository.save(entity);

        CompleteProfile completeProfile = repository.findByUserId(userId);
        completeProfile.setUserProfile(entity);
        repository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("profile Created successfully");
        response.setID(entity.getUserProfileId());
        return response;
    }

    @Override
    public ApiResponse updateProfile(Integer userId, ProfileDTO dto) {
        UserProfile profile= profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

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
        profileMapper.toDTO(savedProfile);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact updated successfully");

        return response;
    }


    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfile(Integer userId) {
        return profileRepository.findByUserId(userId).map(profileMapper::toDTO)
                .orElseThrow(() -> new ProfileNotFoundException("profile not found"));

    }

    @Override
    public void deleteProfile(Integer userId) {
        profileRepository.findByUserId(userId);

    }

}