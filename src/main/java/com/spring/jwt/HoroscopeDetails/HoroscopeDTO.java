package com.spring.jwt.HoroscopeDetails;

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

    @NotNull(message = "Date of birth cannot be empty")
    private Date dob;
    @NotNull(message = "Birth time cannot be empty")
    @Size(max = 45, message = "Birth time cannot exceed 45 characters")
    private String time;
    @Size(max = 45, message = "Birth place cannot exceed 45 characters")
    private String birthPlace;
    @NotNull(message = "Rashi cannot be empty")
    @Size(max = 45, message = "Rashi cannot exceed 45 characters")
    private String rashi;
    @NotNull(message = "Nakshatra cannot be empty")
    @Size(max = 45, message = "Nakshatra cannot exceed 45 characters")
    private String nakshatra;
    @NotNull(message = "Charan cannot be empty")
    @Size(max = 45, message = "Charan cannot exceed 45 characters")
    private String charan;
    @NotNull(message = "Nadi cannot be empty")
    @Size(max = 45, message = "Nadi cannot exceed 45 characters")
    private String nadi;
    @NotNull(message = "Gan cannot be empty")
    @Size(max = 45, message = "Gan cannot exceed 45 characters")
    private String gan;
    @NotNull(message = "Mangal cannot be null")
    @Size(max = 45, message = "Mangal cannot exceed 45 characters")
    private String mangal;
    @NotNull(message = "Gotra cannot be empty")
    @Size(max = 45, message = "Gotra cannot exceed 45 characters")
    private String gotra;
    @NotNull(message = "Devak cannot be empty")
    @Size(max = 45, message = "Devak cannot exceed 45 characters")
    private String devak;
//    @Size(max = 45, message = "CompleteProfile cannot exceed 45 characters")
//    private Status status;
}
