package com.spring.jwt.profile;

import com.spring.jwt.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {


        @NotBlank(message = "First name cannot be empty")
        @Size(max = 45, message = "First name cannot exceed 45 characters")
        private String firstName;

        @NotBlank(message = "Last name cannot be empty")
        @Size(max = 45, message = "Last name cannot exceed 45 characters")
        private String lastName;

        @NotBlank(message = "Middle name cannot be empty")
        @Size(max = 45, message = "Middle name cannot exceed 45 characters")
        private String middleName;

        @NotBlank(message = "Address cannot be empty")
        @Size(max = 100, message = "Address cannot exceed 100 characters")
        private String address;

        @NotBlank(message = "Taluka cannot be empty")
        @Size(max = 45, message = "Taluka cannot exceed 45 characters")
        private String taluka;

        @NotBlank(message = "District cannot be empty")
        @Size(max = 45, message = "District cannot exceed 45 characters")
        private String district;

        @NotNull(message = "Pincode cannot be empty")
        @Min(value = 100000, message = "Pincode must be 6 digits")
        @Max(value = 999999, message = "Pincode must be 6 digits")
        private Integer pinCode;

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
        private String mobileNumber;

        @NotBlank(message = "email cannot be empty")
        private String email;

        private String userProfileStatus;  // optional

        @NotNull(message = "Age cannot be empty")
        private Integer age;

        @NotNull(message = "Gender cannot be empty ")
        @Enumerated(EnumType.STRING)
        private Gender gender;

        @NotBlank(message = "Religion cannot be empty")
        private String religion;

        @NotBlank(message = "Caste cannot be empty")
        private String caste;

        @NotBlank(message = "Marital status cannot be empty")
        @Size(max = 45, message = "Marital status cannot exceed 45 characters")
        private String maritalStatus;

        @NotNull(message = "Height cannot be empty")
        private Double height;

        @NotNull(message = "Weight cannot be empty")
        @Min(value = 20, message = "Weight must be above 20 kg")
        @Max(value = 300, message = "Weight must be below 300 kg")
        private Integer weight;

        @NotBlank(message = "Blood group cannot be empty")
        @Pattern(regexp = "^(A|B|AB|O)[+-]$",
                message = "Blood group must be A+, A-, B+, B-, AB+, AB-, O+, or O-")
        private String bloodGroup;

        private String complexion;
        private String diet;

        private Boolean spectacle;
        private Boolean lens;

        @NotNull(message = "Physically challenged field cannot be empty")
        private Boolean physicallyChallenged;

        private String homeTownDistrict;
        private String nativeTaluka;
        private String currentCity;

        private String userProfileCol;


    }

