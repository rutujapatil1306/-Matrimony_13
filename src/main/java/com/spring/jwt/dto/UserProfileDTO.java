package com.spring.jwt.dto;

import com.spring.jwt.CompleteProfile.FullProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private UserDTO user;
    private FullProfileDTO fullProfileDTO;

    private Set<String> roles;
}
