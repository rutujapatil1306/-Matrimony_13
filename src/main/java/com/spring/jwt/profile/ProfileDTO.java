package com.spring.jwt.profile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    @NotNull(message = "First name cannot be empty")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    private String firstName;
    @NotNull(message = "Last name cannot be empty")
    @Size(max = 45, message = "Last name cannot exceed 45 characters")
    private String lastName;
    @NotNull(message = "Middle name cannot be empty")
    @Size(max = 45, message = "Middle name cannot exceed 45 characters")
    private String middleName;
    @NotNull(message = "Address cannot be empty")
    @Size(max = 45, message = "Address cannot exceed 45 characters")
    private String address;
    @NotNull(message = "Taluka cannot be empty")
    @Size(max = 45, message = "Taluka cannot exceed 45 characters")
    private String taluka;
    @NotNull(message = "District cannot be empty")
    @Size(max = 45, message = "District cannot exceed 45 characters")
    private String district;
    @NotNull(message = "Pincode cannot be empty")
    @Size(max = 45, message = "Pincode cannot exceed 45 characters")
    private Integer pinCode;
    @NotNull(message = "Mobile number cannot be empty")
    private Long mobileNumber;
    private String userProfileStatus;
    private String gender;
    private String religion;
    private String caste;
    @NotNull(message = "Marital status cannot be empty")
    @Size(max = 45, message = "Marital status cannot exceed 45 characters")
    private String maritalStatus;
    @NotNull(message = "Height cannot be empty")
    @Size(max = 45, message = "Height cannot exceed 45 characters")
    private Double height;
    @NotNull(message = "Weight cannot be empty")
    @Size(max = 45, message = "Weight cannot exceed 45 characters")
    private Double weight;
    @NotNull(message = "Blood group cannot be empty")
    @Size(max = 45, message = "Blood group cannot exceed 45 characters")
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
