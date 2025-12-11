package com.spring.jwt.dto;

import com.spring.jwt.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class DisplayProfileDTO {

    // Selected from ProfileDTO
    private String lastName;
    private Integer age;
    private String height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String city;
    private String caste;
    private Integer weight;
    private String bloodGroup;
    private Boolean spectacle;
    private String complexion;
    private Boolean physicallyChallenged;
    private String diet;
    private Boolean lens;


    // Selected from EducationDTO
    private String education;
    private String degree;
    private String occupation;
    private String occupationDetails;
    private Integer incomePerYear;

    // Selected from HoroscopeDTO
    private Date dob;
    private String time;
    private String birthPlace;
    private String rashi;
    private String nakshatra;
    private String charan;
    private String nadi;
    private String gan;
    private String mangal;
    private String gotra;
    private String devak;


    // Selected from FamilyDTO
    private Boolean father;
    private Boolean mother;
    private Integer brothers;
    private Integer marriedBrothers;
    private Integer sisters;
    private Integer marriedSisters;
    private String parentResiding;
    private String parentOccupation;
    private String familyWealth;
    private String mamaSurname;
    private String mamaPlace;
    private String relativeSurnames;


    // Selected from partnerPreference
    private String preferredCity;
    private String preferredEducation;
    private String preferredOccupation;
    private Integer expectedIncome;
    private Boolean mangalAcceptable;
    private String expectedCaste;
    private Double expectedHeight;

}
