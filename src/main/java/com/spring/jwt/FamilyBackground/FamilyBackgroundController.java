package com.spring.jwt.FamilyBackground;

import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/familyBackground")
@RequiredArgsConstructor
public class FamilyBackgroundController {


    private final FamilyBackgroundService service;


    @Operation(summary = "Api for FamilyBackground creation")
    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> saveFamilyBackground(
            @RequestBody @Valid FamilyBackgroundDTO dto) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response= service.saveFamilyBackground(userId, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @Operation(summary = "Fetching FamilyBackground details using user id")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<FamilyBackgroundDTO>> getFamilyBackgroundById() {

        Integer userId = SecurityUtil.getCurrentUserId();
        FamilyBackgroundDTO background = service.getBackground(userId);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("FamilyBackground Details For Id " + userId, background));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> updateByUserID(
            @RequestBody FamilyBackgroundDTO dto){

        Integer userId = SecurityUtil.getCurrentUserId();
        service.updateBackground(userId,dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Family Background Updated Successfully !"));
    }
}
