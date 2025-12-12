package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/education")
public class EducationController {

    private final EducationService educationAndProfessionService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> createEducationAndProfess(
            @RequestBody @Valid EducationDTO educationDTO) {

        Integer userId= SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = educationAndProfessionService.createEducationAndProfession(userId , educationDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<EducationDTO>> updateEducationAndProfession(
            @RequestBody @Valid EducationDTO educationDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = educationAndProfessionService.updateEducationAndProfession(userId, educationDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Education details updated successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<EducationDTO>> getEducationAndProfession(){

        Integer userId = SecurityUtil.getCurrentUserId();
        EducationDTO educationAndProfession = educationAndProfessionService.getEducationAndProfession(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Education and profession details For userId " + userId, educationAndProfession));
    }

}


