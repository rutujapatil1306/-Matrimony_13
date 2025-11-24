package com.spring.jwt.profile;

import com.spring.jwt.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO toDTO(UserProfile profile) {
        if (profile == null) {
            return null;
        }

        ProfileDTO dto = new ProfileDTO();

        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setMiddleName(profile.getMiddleName());
        dto.setAddress(profile.getAddress());
        dto.setTaluka(profile.getTaluka());
        dto.setDistrict(profile.getDistrict());
        dto.setPinCode(profile.getPinCode());
        dto.setMobileNumber(profile.getMobileNumber());
        //dto.setCompleteProfile(profile.getCompleteProfile());
        dto.setGender(profile.getGender());
        dto.setReligion(profile.getReligion());
        dto.setCaste(profile.getCaste());
        dto.setMaritalStatus(profile.getMaritalStatus());
        dto.setHeight(profile.getHeight());
        dto.setBloodGroup(profile.getBloodGroup());
        dto.setWeight(profile.getWeight());
        dto.setComplexion(profile.getComplexion());
        dto.setDiet(profile.getDiet());
        dto.setSpectacle(profile.getSpectacle());
        dto.setLens(profile.getLens());
        dto.setPhysicallyChallenged(profile.getPhysicallyChallenged());
        dto.setHomeTownDistrict(profile.getHomeTownDistrict());
        dto.setNativeTaluka(profile.getNativeTaluka());
        dto.setCurrentCity(profile.getCurrentCity());
        dto.setUserProfileCol(profile.getUserProfileCol());

        return dto;
    }

    public UserProfile toEntity(ProfileDTO dto) {
        if (dto == null){
            return null;
        }

        UserProfile profile = new UserProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setAddress(dto.getAddress());
        profile.setTaluka(dto.getTaluka());
        profile.setDistrict(dto.getDistrict());
        profile.setPinCode(dto.getPinCode());
        profile.setMobileNumber(dto.getMobileNumber());
        //profile.setCompleteProfile(dto.getCompleteProfile());
        profile.setGender(dto.getGender());
        profile.setReligion(dto.getReligion());
        profile.setCaste(dto.getCaste());
        profile.setMaritalStatus(dto.getMaritalStatus());
        profile.setHeight(dto.getHeight());
        profile.setBloodGroup(dto.getBloodGroup());
        profile.setWeight(dto.getWeight());
        profile.setComplexion(dto.getComplexion());
        profile.setDiet(dto.getDiet());
        profile.setSpectacle(dto.getSpectacle());
        profile.setLens(dto.getLens());
        profile.setPhysicallyChallenged(dto.getPhysicallyChallenged());
        profile.setHomeTownDistrict(dto.getHomeTownDistrict());
        profile.setNativeTaluka(dto.getNativeTaluka());
        profile.setCurrentCity(dto.getCurrentCity());
        profile.setUserProfileCol(dto.getUserProfileCol());

        return profile;
    }
}
