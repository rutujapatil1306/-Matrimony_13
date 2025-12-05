package com.spring.jwt.PartnerPreference;

import com.spring.jwt.entity.PartnerPreference;
import org.springframework.stereotype.Component;

@Component
public class PartnerPreferenceMapper {

    public PartnerPreferenceDTO toDTO(PartnerPreference p) {
        if (p == null){
            return null;
        }
        PartnerPreferenceDTO dto = new PartnerPreferenceDTO();
        dto.setPartnerAge(p.getAge());
        dto.setLookingFor(p.getLookingFor());
        dto.setPartnerHeight(p.getHeight());
        dto.setEatingHabits(p.getEatingHabits());
        dto.setPartnerComplexion(p.getComplexion());
        dto.setCountryLivingIn(p.getCountryLivingIn());
        dto.setCityLivingIn(p.getCityLivingIn());
        dto.setPartnerReligion(p.getReligion());
        dto.setPartnerCaste(p.getCaste());
        dto.setPartnerEducation(p.getEducation());
        dto.setPartnerResidentStatus(p.getResidentStatus());
        dto.setPartnerOccupation(p.getPartnerOccupation());
        dto.setPartnerIncome(p.getPartnerIncome());
        dto.setMangal(p.getMangal());

        return dto;
    }

    public PartnerPreference toEntity(PartnerPreferenceDTO dto) {
        if (dto == null) return null;

        PartnerPreference p = new PartnerPreference();

        p.setAge(dto.getPartnerAge());
        p.setLookingFor(dto.getLookingFor());
        p.setHeight(dto.getPartnerHeight());
        p.setEatingHabits(dto.getEatingHabits());
        p.setComplexion(dto.getPartnerComplexion());
        p.setCountryLivingIn(dto.getCountryLivingIn());
        p.setCityLivingIn(dto.getCityLivingIn());
        p.setReligion(dto.getPartnerReligion());
        p.setCaste(dto.getPartnerCaste());
        p.setEducation(dto.getPartnerEducation());
        p.setResidentStatus(dto.getPartnerResidentStatus());
        p.setPartnerOccupation(dto.getPartnerOccupation());
        p.setPartnerIncome(dto.getPartnerIncome());
        p.setMangal(dto.getMangal());

        return p;
    }
}
