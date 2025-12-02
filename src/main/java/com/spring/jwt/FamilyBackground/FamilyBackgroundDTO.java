package com.spring.jwt.FamilyBackground;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "brothers field required")
    private String brothers;
    @NotBlank(message = "married brothers field required")
    private String marriedBrothers;
    @NotBlank(message = "sisters field required")
    private String sisters;
    @NotBlank(message = "married sisters field required")
    private String marriedSisters;
    @NotBlank(message = "inter caste in family field required")
    private Boolean interCasteInFamily;
    @NotBlank(message = "parent residing field required")
    private String parentResiding;
    @NotBlank(message = "mama surname required")
    private String mamaSurname;
    @NotBlank(message = "mama place required")
    private String mamaPlace;
    private String familyBackgroundCol;
    @NotBlank(message = "relatives surnames required")
    private String relativeSurnames;
}
