package com.spring.jwt.profile;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface ProfileService {

    BaseResponseDTO createProfile(Integer userId, ProfileDTO profileDTO);

    ApiResponse updateProfile(Integer userId, ProfileDTO dto);

    ProfileDTO getProfile(Integer userId);

    void deleteProfile(Integer userId);
}
