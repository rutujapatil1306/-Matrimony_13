package com.spring.jwt.SearchFiltter;


import com.spring.jwt.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterDTO {

    private Gender gender;
    private String maritalStatus;
    private Integer ageFrom;
    private Integer ageTo;
    private Integer heightFrom;
    private Integer heightTo;
    private String occupation;
    private String education;
    private String city;
    private String district;
}
