package com.spring.jwt.CompleteProfile;

import com.spring.jwt.ContactDetails.ContactDTO;
import com.spring.jwt.Document.DocumentDTO;
import com.spring.jwt.EducationAndProfession.EducationDTO;
import com.spring.jwt.FamilyBackground.FamilyBackgroundDTO;
import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.PartnerPreference.PartnerPreferenceDTO;
import com.spring.jwt.profile.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullProfileDTO{

    private ProfileDTO profileDTO;
    private EducationDTO educationDTO;
    private HoroscopeDTO horoscopeDTO;
    private PartnerPreferenceDTO partnerPreferenceDTO;
    private ContactDTO contactDTO;
    private FamilyBackgroundDTO familyBackgroundDTO;
    private DocumentDTO documentDTO;
    private boolean profileCompleted;


} 