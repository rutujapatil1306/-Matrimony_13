package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.ContactDetails.ContactDTO;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education")
public class EducationAndProfessionController {

    private final EducationAndProfessionService educationAndProfessionService;

    private final JwtUtils jwtUtils;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> create(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody EducationDTO educationDTO) {
        Integer userId= jwtUtils.extractUSerID(authHeader);
        BaseResponseDTO response = educationAndProfessionService.create(educationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<EducationAndProfession>> create(@RequestParam Integer userID,
                                                                      @RequestBody EducationDTO educationDTO){
        ApiResponse response = educationAndProfessionService.save(userID,educationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
