package com.spring.jwt.FamilyBackground;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyBackgroundDTO {


    private String fatherOccupation;
    private String motherOccupation;
    private String brothers;
    private String marriedBrothers;
    private String sisters;
    private String marriedSisters;
    private Boolean interCasteInFamily;
    private String parentResiding;
    private String mamaSurname;
    private String mamaPlace;
    private String familyBackgroundCol;
    private String relativeSurnames;
}
