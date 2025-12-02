package com.spring.jwt.HoroscopeDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoroscopeDTO {

    @NotBlank(message = "Date of birth required")
    private Date dob;
    @NotBlank(message = "Birth time required")
    private String time;
    @NotBlank(message = "birthplace required")
    private String birthPlace;
    @NotBlank(message = "Rashi required")
    private String rashi;
    @NotBlank(message = "Nakshatra required")
    private String nakshatra;
    @NotBlank(message = "Charan required")
    private String charan;
    @NotBlank(message = "Nadi required")
    private String nadi;
    @NotBlank(message = "Gan required")
    private String gan;
    @NotBlank(message = "Mangal required")
    private String mangal;
    @NotBlank(message = "Gotra required")
    private String gotra;
    @NotBlank(message = "Devak required")
    private String devak;
//    @Size(max = 45, message = "CompleteProfile cannot exceed 45 characters")
//    private Status status;
}
