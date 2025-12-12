package com.spring.jwt.CompleteProfile;

import com.spring.jwt.Enums.Gender;
import com.spring.jwt.dto.DisplayProfileDTO;
import com.spring.jwt.dto.PublicProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompleteProfileService {

    FullProfileDTO getFullProfile(Integer userId);
    DisplayProfileDTO getDisplayProfile(Integer userId);
    Page<PublicProfileDTO> getProfileByGender(Pageable pageable, Gender gender);


    }
