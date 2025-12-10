package com.spring.jwt.EducationAndProfession;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    @NotBlank(message = "Education field cannot be empty")
    private String education;

    @NotBlank(message = "Degree required")
    private String degree;

    @NotBlank(message = "Occupation required")
    private String occupation;

    @NotBlank(message = "Occupation details required")
    private String occupationDetails;

    @NotNull(message = "Income per year required")
    @Positive(message = "Income per year must be a positive number")
    private Integer incomePerYear;

    private String status;

    private String educationAndProfessionalDetailsCol;

}
