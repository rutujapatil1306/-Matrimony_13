package com.spring.jwt.CompleteProfile;

import com.spring.jwt.Document.DocumentRepository;
import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.Document;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PublicProfileMapper {

    public static PublicProfileDTO toDTO(CompleteProfile profile) {
        PublicProfileDTO dto = new PublicProfileDTO();

        dto.setUserId(profile.getUser().getId());
        dto.setFirstName(profile.getUserProfile().getFirstName());
        dto.setGender(profile.getUserProfile().getGender());
        dto.setCity(profile.getContactDetails().getCity());
        dto.setEducation(profile.getEducationAndProfession().getEducation());
        dto.setDateOfBirth(profile.getHoroscopeDetails().getDob());
        dto.setHeight(profile.getUserProfile().getHeight());
        dto.setCaste(profile.getUserProfile().getCaste());
        dto.setOccupation(profile.getEducationAndProfession().getOccupation());

        return dto;
    }
}
