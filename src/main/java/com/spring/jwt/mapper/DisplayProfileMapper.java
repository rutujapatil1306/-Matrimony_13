package com.spring.jwt.mapper;

import com.spring.jwt.EducationAndProfession.EducationDTO;
import com.spring.jwt.FamilyBackground.FamilyBackgroundDTO;
import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.PartnerPreference.PartnerPreferenceDTO;
import com.spring.jwt.dto.DisplayProfileDTO;
import com.spring.jwt.profile.ProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class DisplayProfileMapper {

    public DisplayProfileDTO toDTO(
            ProfileDTO profile,
            EducationDTO education,
            HoroscopeDTO horoscope,
            FamilyBackgroundDTO family,
            PartnerPreferenceDTO partnerPreference
    ) {
        DisplayProfileDTO dto = new DisplayProfileDTO();

        // --- PROFILE ---
        if (profile != null) {
            dto.setLastName(profile.getLastName());
            dto.setAge(profile.getAge());
            dto.setHeight(profile.getHeight());
            dto.setGender(profile.getGender());
            dto.setCaste(profile.getCaste());
            dto.setCity(profile.getCurrentCity());
            dto.setWeight(profile.getWeight());
            dto.setBloodGroup(profile.getBloodGroup());
            dto.setSpectacle(profile.getSpectacle());
            dto.setComplexion(profile.getComplexion());
            dto.setPhysicallyChallenged(profile.getPhysicallyChallenged());
            dto.setDiet(profile.getDiet());
            dto.setLens(profile.getLens());
        }

        // --- EDUCATION ---
        if (education != null) {
            dto.setEducation(education.getEducation());
            dto.setOccupation(education.getOccupation());
            dto.setOccupationDetails(education.getOccupationDetails());
            dto.setDegree(education.getDegree());
            dto.setIncomePerYear(education.getIncomePerYear());
        }

        // --- HOROSCOPE ---
        if (horoscope != null) {
            dto.setDob(horoscope.getDob());
            dto.setTime(horoscope.getTime());
            dto.setBirthPlace(horoscope.getBirthPlace());
            dto.setRashi(horoscope.getRashi());
            dto.setNakshatra(horoscope.getNakshatra());
            dto.setCharan(horoscope.getCharan());
            dto.setNadi(horoscope.getNadi());
            dto.setGan(horoscope.getGan());
            dto.setGotra(horoscope.getGotra());
            dto.setMangal(horoscope.getMangal());
            dto.setDevak(horoscope.getDevak());
        }

        // --- FAMILY ---
        if (family != null) {
            dto.setFather(Boolean.valueOf((family.getFathersName() != null && !family.getFathersName().trim().isEmpty())
                            ? "Yes" : "No"));

            dto.setMother(Boolean.valueOf((family.getMothersName() != null && !family.getMothersName().trim().isEmpty())
                            ? "Yes" : "No"));
            dto.setBrothers(family.getBrothers());
            dto.setMarriedBrothers(family.getMarriedBrothers());
            dto.setSisters(family.getSisters());
            dto.setMarriedSisters(family.getMarriedSisters());
            dto.setParentResiding(family.getParentResiding());
            dto.setParentOccupation(family.getFatherOccupation());
            dto.setFamilyWealth(family.getFamilyWealth());
            dto.setMamaSurname(family.getMamaSurname());
            dto.setMamaPlace(family.getMamaPlace());
            dto.setRelativeSurnames(family.getRelativeSurnames());
        }


        //-------partnerPreference--------

        if(partnerPreference != null){
            dto.setPreferredCity(partnerPreference.getCityLivingIn());
            dto.setPreferredEducation(partnerPreference.getPartnerEducation());
            dto.setPreferredOccupation(partnerPreference.getPartnerOccupation());
            dto.setExpectedIncome(partnerPreference.getPartnerIncome());
            dto.setExpectedHeight(partnerPreference.getPartnerHeight());
            dto.setExpectedCaste(partnerPreference.getPartnerCaste());
            dto.setMangalAcceptable(partnerPreference.getMangal());
        }
//        // --- DOCUMENTS ---
//        if (document != null) {
//            dto.(document.getProfilePhotoUrl());
//        }
//
//        // --- CONTACT (only logged-in) ---
//        if (contact != null) {
//            dto.setMobileNumber(contact.getMobileNumber());
//        }

        return dto;
    }
}
