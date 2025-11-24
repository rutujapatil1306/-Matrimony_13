package com.spring.jwt.HoroscopeDetails;


import com.spring.jwt.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @PatchMapping("/saveHoroscope")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> saveHoroscope(
            @RequestBody HoroscopeDTO horoscopeDTO) {
        try {
            horoscopeDetailsService.saveHoroscopeDetails(horoscopeDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Horoscope details saved successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST, "saving horoscope details failed", e.getMessage()));
        }
    }

    @Operation(summary = "Api for fetching horoscope through its unique identifier")
    @GetMapping("/getHoroscope")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> getHoroscope(
            @PathVariable @Parameter Integer id) {
        try {
            HoroscopeDTO horoscope = horoscopeDetailsService.getHoroscopeById(id);
            return ResponseEntity.ok()
                    .body(ApiResponse.success("Horoscope fetched successfully", horoscope));
        }catch (HoroscopeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND,"Horoscope not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST, "fetching horoscope failed", e.getMessage()));
        }
    }

    @Operation(summary = "Api for updating horoscope through its unique identifier")
    @PatchMapping("/updateHoroscope")
    public ResponseEntity<ApiResponse<HoroscopeDTO>> updateHoroscope(
            @PathVariable @Parameter Integer id,
            @RequestBody HoroscopeDTO horoscopeDTO) {
        try{
            HoroscopeDTO horoscopeDTO1 = horoscopeDetailsService.updateHoroscope(id, horoscopeDTO);
            return ResponseEntity.ok()
                    .body(ApiResponse.success("Horoscope details updated successfully"));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST,"horoscope update failed", e.getMessage()));
        }

    }

}