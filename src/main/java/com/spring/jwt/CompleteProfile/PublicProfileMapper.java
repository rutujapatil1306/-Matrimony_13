package com.spring.jwt.CompleteProfile;

import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.entity.CompleteProfile;
import org.springframework.stereotype.Component;

@Component
public class PublicProfileMapper {

    public static PublicProfileDTO toDTO(CompleteProfile profile) {
        PublicProfileDTO dto = new PublicProfileDTO();

        dto.setFirstName(profile.getUserProfile().getFirstName());
        dto.setGender(profile.getUserProfile().getGender());
        dto.setCity(profile.getContactDetails().getCity());
        dto.setEducation(profile.getEducationAndProfession().getEducation());
        dto.setDateOfBirth(profile.getHoroscopeDetails().getDob());
        dto.setHeight(profile.getUserProfile().getHeight());
        dto.setCaste(profile.getUserProfile().getCaste());
        dto.setOccupation(profile.getEducationAndProfession().getOccupation());
       // dto.setProfilePhoto(profile.getDocument().getProfilePhoto());

        return dto;
    }
}
