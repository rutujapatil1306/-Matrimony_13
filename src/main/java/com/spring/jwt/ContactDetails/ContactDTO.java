package com.spring.jwt.ContactDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ContactDTO {

    @NotBlank(message = "Full address required")
    private String fullAddress;

    @NotBlank(message = "City required")
    private String city;

    @NotNull(message = "Pincode required")
    @Positive(message = "pincode should be positive")
    private Integer pinCode;

    @NotNull(message = "mobile number required")
    private Long mobileNumber;

    @NotNull(message = "alternate number required")
    private Long alternateNumber;

}
