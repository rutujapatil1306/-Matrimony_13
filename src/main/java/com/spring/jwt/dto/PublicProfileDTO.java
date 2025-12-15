package com.spring.jwt.dto;

import com.spring.jwt.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class  PublicProfileDTO {

    private Integer userId;
    private String lastName;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Double height;
    private String city;
    private String education;
    private String occupation;
    private String caste;
    private String profilePhoto;
}
