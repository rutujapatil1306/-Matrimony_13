package com.spring.jwt.CompleteProfile;

import com.spring.jwt.ContactDetails.ContactDTO;
import com.spring.jwt.ContactDetails.ContactMapper;
import com.spring.jwt.ContactDetails.ContactService;
import com.spring.jwt.Document.DocumentService;
import com.spring.jwt.EducationAndProfession.EducationDTO;
import com.spring.jwt.EducationAndProfession.EducationService;
import com.spring.jwt.FamilyBackground.FamilyBackgroundDTO;
import com.spring.jwt.FamilyBackground.FamilyBackgroundService;
import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.HoroscopeDetails.HoroscopeDetailsService;
import com.spring.jwt.PartnerPreference.PartnerPreferenceDTO;
import com.spring.jwt.PartnerPreference.PartnerPreferenceService;
import com.spring.jwt.dto.DisplayProfileDTO;
import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.Enums.Gender;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.mapper.DisplayProfileMapper;
import com.spring.jwt.profile.ProfileDTO;
import com.spring.jwt.profile.ProfileService;
import com.spring.jwt.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompleteProfileServiceImpl implements CompleteProfileService {

    private final CompleteProfileRepository completeProfileRepository;
    private final FullProfileMapper mapper;


    @Override
    public FullProfileDTO getFullProfile(Integer userId) {

        CompleteProfile cp = completeProfileRepository
                .findFullProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Profile not found for userId : " + userId));

        return mapper.toDTO(cp);
    }

    @Override
    public FullProfileDTO getDisplayProfile(Integer userId) {

        CompleteProfile cp = completeProfileRepository
                .findFullProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("profile not found for userId : " + userId));

        return mapper.toDTO(cp);

    }

    @Override
    public Page<PublicProfileDTO> getProfileByGender(Pageable pageable, Gender gender) {
        Page<CompleteProfile> profiles =
                completeProfileRepository.findByUserProfileGender(gender, pageable);

        return profiles.map(PublicProfileMapper::toDTO);
    }

}
