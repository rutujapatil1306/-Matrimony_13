package com.spring.jwt.profile;


import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.HoroscopeDetails.HelperUtil;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

        if (profileRepository.existsByUserId(userId)) {
            throw new DuplicateResourceException(
                    "Profile details already exist for this user"
            );
        }


        profileRepository.findByMobileNumberOrEmail(dto.getMobileNumber(), dto.getEmail())
                .ifPresent(p -> {
                    throw new UserAlreadyExistException("Mobile Number or Email is already registered!");
                });

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
    @Transactional
    public ApiResponse updateProfile(Integer userId, ProfileDTO dto) {
        UserProfile profile= profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        profileRepository.findByMobileNumberOrEmail(dto.getMobileNumber(), dto.getEmail())
                .ifPresent(p -> {
                    throw new UserAlreadyExistException("Mobile Number or Email is already registered!");
                });

        HelperUtil.getDataIfNotNull(dto::getFirstName, profile::setFirstName);
        HelperUtil.getDataIfNotNull(dto::getLastName, profile::setLastName);
        HelperUtil.getDataIfNotNull(dto::getMiddleName, profile::setMiddleName);
        HelperUtil.getDataIfNotNull(dto::getAddress, profile::setAddress);
        HelperUtil.getDataIfNotNull(dto::getTaluka, profile::setTaluka);
        HelperUtil.getDataIfNotNull(dto::getDistrict, profile::setDistrict);
        HelperUtil.getDataIfNotNull(dto::getPinCode, profile::setPinCode);
        HelperUtil.getDataIfNotNull(dto::getMobileNumber, profile::setMobileNumber);
        HelperUtil.getDataIfNotNull(dto::getGender, profile::setGender);
        HelperUtil.getDataIfNotNull(dto::getReligion, profile::setReligion);
        HelperUtil.getDataIfNotNull(dto::getCaste, profile::setCaste);
        HelperUtil.getDataIfNotNull(dto::getMaritalStatus, profile::setMaritalStatus);
        HelperUtil.getDataIfNotNull(dto::getHeight, profile::setHeight);
        HelperUtil.getDataIfNotNull(dto::getWeight, profile::setWeight);
        HelperUtil.getDataIfNotNull(dto::getBloodGroup, profile::setBloodGroup);
        HelperUtil.getDataIfNotNull(dto::getComplexion, profile::setComplexion);
        HelperUtil.getDataIfNotNull(dto::getDiet, profile::setDiet);
        HelperUtil.getDataIfNotNull(dto::getSpectacle, profile::setSpectacle);
        HelperUtil.getDataIfNotNull(dto::getLens, profile::setLens);
        HelperUtil.getDataIfNotNull(dto::getPhysicallyChallenged, profile::setPhysicallyChallenged);
        HelperUtil.getDataIfNotNull(dto::getHomeTownDistrict, profile::setHomeTownDistrict);
        HelperUtil.getDataIfNotNull(dto::getNativeTaluka, profile::setNativeTaluka);
        HelperUtil.getDataIfNotNull(dto::getCurrentCity, profile::setCurrentCity);
        HelperUtil.getDataIfNotNull(dto::getUserProfileCol, profile::setUserProfileCol);

        UserProfile savedProfile = profileRepository.save(profile);
        profileMapper.toDTO(savedProfile);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Profile updated successfully");
        response.setData(response);

        return response;
    }


    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfile(Integer userId) {
        return profileRepository.findByUserId(userId).map(profileMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("profile details not found"));

    }

    @Transactional
    @Override
    public BaseResponseDTO deleteProfile(Integer userID) {

        UserProfile userProfile = profileRepository.findByUserId(userID)
                .orElseThrow(() -> new ResourceNotFoundException("Profile details not found"));

        profileRepository.delete(userProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("profile details deleted Successfully");

        return response;
    }
}