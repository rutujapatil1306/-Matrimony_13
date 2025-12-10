package com.spring.jwt.CompleteProfile;

import com.spring.jwt.ContactDetails.ContactMapper;
import com.spring.jwt.EducationAndProfession.EducationMapper;
import com.spring.jwt.FamilyBackground.FamilyBackgroundMapper;
import com.spring.jwt.HoroscopeDetails.HoroscopeMapper;
import com.spring.jwt.PartnerPreference.PartnerPreferenceMapper;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.profile.ProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class FullProfileMapper {


        private final ProfileMapper profileMapper;
        private final EducationMapper educationMapper;
        private final HoroscopeMapper horoscopeMapper;
        private final FamilyBackgroundMapper familyBackgroundMapper;
        private final ContactMapper contactMapper;
        private final PartnerPreferenceMapper partnerPreferenceMapper;

        public FullProfileMapper(ProfileMapper userProfileMapper,
                                 EducationMapper educationMapper,
                                 HoroscopeMapper horoscopeMapper,
                                 FamilyBackgroundMapper familyBackgroundMapper,
                                 ContactMapper contactMapper,
                                 PartnerPreferenceMapper partnerPreferenceMapper) {
            this.profileMapper = userProfileMapper;
            this.educationMapper = educationMapper;
            this.horoscopeMapper = horoscopeMapper;
            this.familyBackgroundMapper = familyBackgroundMapper;
            this.contactMapper = contactMapper;
            this.partnerPreferenceMapper = partnerPreferenceMapper;
        }

        public FullProfileDTO toDTO(CompleteProfile cp) {

            FullProfileDTO dto = new FullProfileDTO();

            dto.setProfileCompleted(cp.isProfileCompleted());

            // ✔ Map each module using existing mappers
            if (cp.getUserProfile() != null) {
                dto.setProfileDTO(profileMapper.toDTO(cp.getUserProfile()));
            }

            if (cp.getEducationAndProfession() != null) {
                dto.setEducationDTO(educationMapper.toDTO(cp.getEducationAndProfession()));
            }

            if (cp.getHoroscopeDetails() != null) {
                dto.setHoroscopeDTO(horoscopeMapper.toDTO(cp.getHoroscopeDetails()));
            }

            if (cp.getFamilyBackground() != null) {
                dto.setFamilyBackgroundDTO(familyBackgroundMapper.toDTO(cp.getFamilyBackground()));
            }

            if (cp.getPartnerPreference() != null) {
                dto.setPartnerPreferenceDTO(partnerPreferenceMapper.toDTO(cp.getPartnerPreference()));
            }

            if (cp.getContactDetails() != null) {
                dto.setContactDTO(contactMapper.toDTO(cp.getContactDetails()));
            }

            return dto;
        }
    }


