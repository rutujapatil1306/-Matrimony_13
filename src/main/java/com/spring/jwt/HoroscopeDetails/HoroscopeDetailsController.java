package com.spring.jwt.HoroscopeDetails;



import com.spring.jwt.ContactDetails.ContactDTO;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.JwtUtils;
import com.spring.jwt.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HoroscopeDetailsController {

    private final HoroscopeDetailsService horoscopeDetailsService;


    @Operation(summary = "Api for profile creation")
    @PostMapping("/saveHoroscope")
    public ResponseEntity<BaseResponseDTO> saveHoroscope(
            @RequestBody HoroscopeDTO horoscopeDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response= horoscopeDetailsService.saveHoroscopeDetails(userId, horoscopeDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Fetching horoscope details using user id")
    @GetMapping("/getHoroscope")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> getHoroscopeByID() {

        Integer userId = SecurityUtil.getCurrentUserId();
        HoroscopeDTO horoscopeById = horoscopeDetailsService.getHoroscopeById(userId);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Horoscope Details For Id " + userId, horoscopeById));
    }

    @PatchMapping("/updateHoroscope")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> updateByUserID(
            @RequestBody HoroscopeDTO horoscopeDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        horoscopeDetailsService.updateHoroscope(userId, horoscopeDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Horoscope Updated Successfully !"));
    }
}