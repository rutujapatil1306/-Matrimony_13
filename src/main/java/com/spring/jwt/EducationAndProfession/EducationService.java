package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;

public interface EducationService {

    BaseResponseDTO create(Integer userID ,EducationDTO educationDTO);

    ApiResponse updateByUserdID(Integer userID, EducationDTO educationDTO);

    EducationDTO getByUserId(Integer userId);
}
