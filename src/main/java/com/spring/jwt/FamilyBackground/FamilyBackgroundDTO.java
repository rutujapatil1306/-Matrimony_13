package com.spring.jwt.FamilyBackground;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyBackgroundDTO {


    @NotBlank(message = "Fathers name required")
    private String fathersName;

    @NotBlank(message = "Fathers occupation required")
    private String fatherOccupation;

    @NotBlank(message = "Mothers name required")
    private String mothersName;

    @NotBlank(message = "Mother occupation required")
    private String motherOccupation;

    @NotNull(message = "brothers field required")
    private Integer brothers;

    @NotNull(message = "married brothers field required")
    private Integer marriedBrothers;

    @NotNull(message = "sisters field required")
    private Integer sisters;

    @NotNull(message = "married sisters field required")
    private Integer marriedSisters;

    @NotNull(message = "inter caste in family field required")
    private Boolean interCasteInFamily;

    @NotBlank(message = "parent residing field required")
    private String parentResiding;

    private String familyWealth;

    @NotBlank(message = "mama surname required")
    private String mamaSurname;

    @NotBlank(message = "mama place required")
    private String mamaPlace;

    private String familyBackgroundCol;

    @NotBlank(message = "relatives surnames required")
    private String relativeSurnames;
}
