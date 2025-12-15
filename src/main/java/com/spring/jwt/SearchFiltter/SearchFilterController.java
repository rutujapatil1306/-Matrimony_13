package com.spring.jwt.SearchFiltter;


import com.spring.jwt.Enums.Gender;
import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchFilterController {


    private final SearchFilterService searchFilterService;
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<Page<PublicProfileDTO>>> searchProfiles(

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number cannot be negative") int page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 30, message = "Page size cannot exceed 30") int size,

            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String maritalStatus,
            @RequestParam(required = false) Integer ageFrom,
            @RequestParam(required = false) Integer ageTo,
            @RequestParam(required = false) Integer heightFrom,
            @RequestParam(required = false) Integer heightTo,
            @RequestParam(required = false) String occupation,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String district
    ) {

        Integer userId = SecurityUtil.getCurrentUserId();

        Pageable pageable = PageRequest.of(page, size, Sort.by("completeProfileId").descending());

        SearchFilterDTO filter = new SearchFilterDTO(
                gender,
                maritalStatus,
                ageFrom,
                ageTo,
                heightFrom,
                heightTo,
                occupation,
                education,
                city,
                district
        );

        Page<PublicProfileDTO> result = searchFilterService.searchProfiles(filter, pageable,userId);

        return ResponseEntity.ok(
                ApiResponse.success("Profiles fetched successfully", result)
        );
    }

}




