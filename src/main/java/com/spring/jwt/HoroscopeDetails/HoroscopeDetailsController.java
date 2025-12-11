package com.spring.jwt.HoroscopeDetails;



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
@RequestMapping("/api/v1/horoscope")
@RequiredArgsConstructor
public class HoroscopeDetailsController {

    private final HoroscopeDetailsService horoscopeDetailsService;


    @Operation(summary = "Api for Horoscope creation")
    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> saveHoroscope(
            @RequestBody @Valid HoroscopeDTO horoscopeDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response= horoscopeDetailsService.saveHoroscopeDetails(userId, horoscopeDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Fetching horoscope details using user id")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> getHoroscope() {

        Integer userId = SecurityUtil.getCurrentUserId();
        HoroscopeDTO horoscopeById = horoscopeDetailsService.getHoroscopeById(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Horoscope Details For Id " + userId, horoscopeById));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> updateHoroscope(
            @RequestBody @Valid HoroscopeDTO horoscopeDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        horoscopeDetailsService.updateHoroscope(userId, horoscopeDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Horoscope details Updated Successfully !"));
    }
}