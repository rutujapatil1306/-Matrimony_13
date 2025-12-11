package com.spring.jwt.SearchFiltter;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilterDTO {

    private String gender;
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
