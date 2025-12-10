package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface EducationService {

    BaseResponseDTO createEducationAndProfession(Integer userID ,EducationDTO educationDTO);

    ApiResponse updateEducationAndProfession(Integer userID, EducationDTO educationDTO);

    EducationDTO getEducationAndProfession(Integer userId);

}
