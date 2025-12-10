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

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String alternateNumber;

}
