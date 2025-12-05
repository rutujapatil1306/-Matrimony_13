package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.entity.EducationAndProfession;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper {

    public EducationDTO toDTO(EducationAndProfession edu) {
        if (edu == null){
            return null;
        }

        EducationDTO dto = new EducationDTO();

        dto.setEducation(edu.getEducation());
        dto.setDegree(edu.getDegree());
        dto.setOccupation(edu.getOccupation());
        dto.setOccupationDetails(edu.getOccupationDetails());
        dto.setIncomePerYear(edu.getIncomePerYear());
        dto.setEducationAndProfessionalDetailsCol(edu.getEducationAndProfessionalDetailsCol());

        return dto;
    }

    public EducationAndProfession toEntity(EducationDTO dto) {
        if (dto == null){
            return null;
        }

        EducationAndProfession edu = new EducationAndProfession();

        edu.setEducation(dto.getEducation());
        edu.setDegree(dto.getDegree());
        edu.setOccupation(dto.getOccupation());
        edu.setOccupationDetails(dto.getOccupationDetails());
        edu.setIncomePerYear(dto.getIncomePerYear());
        edu.setEducationAndProfessionalDetailsCol(dto.getEducationAndProfessionalDetailsCol());

        return edu;
    }
}
