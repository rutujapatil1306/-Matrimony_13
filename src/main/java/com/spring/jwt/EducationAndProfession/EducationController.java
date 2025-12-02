package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationAndProfessionService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> create(
            @RequestBody EducationDTO educationDTO) {

        Integer userId= SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = educationAndProfessionService.create(userId , educationDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<EducationAndProfession>> updateByUserIdID(
            @RequestBody EducationDTO educationDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = educationAndProfessionService.updateByUserdID(userId,educationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
