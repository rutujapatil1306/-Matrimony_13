package com.spring.jwt.SearchFiltter;


import com.spring.jwt.dto.PublicProfileDTO;
import com.spring.jwt.entity.CompleteProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class SearchServiceImpl implements SearchFilterService {

    private final SearchProfileRepository searchRepo;

    @Override
    public Page<PublicProfileDTO> searchProfiles(SearchFilterDTO filter, Pageable pageable, Integer userId) {
        Page<CompleteProfile> profiles = searchRepo.searchProfiles(
                filter.getGender(),
                filter.getMaritalStatus(),
                filter.getAgeFrom(),
                filter.getAgeTo(),
                filter.getHeightFrom(),
                filter.getHeightTo(),
                filter.getOccupation(),
                filter.getEducation(),
                filter.getCity(),
                filter.getDistrict(),
                pageable
        );

        return profiles.map(this::toDTO);  // <-- DTO conversion here
    }

    // -----------------------------
    // DTO Converter Method
    // -----------------------------
    private PublicProfileDTO toDTO(CompleteProfile cp) {

        PublicProfileDTO dto = new PublicProfileDTO();

        // From UserProfile table
        if (cp.getUserProfile() != null) {
            dto.setLastName(cp.getUserProfile().getLastName());
            dto.setGender(cp.getUserProfile().getGender());
            dto.setDateOfBirth(cp.getHoroscopeDetails().getDob());
            dto.setHeight(cp.getUserProfile().getHeight());
             // dto.setNativeDistrict(cp.getUserProfile().getNativeDistrict());
            // dto.setMaritalStatus(cp.getUserProfile().getMaritalStatus());
        }

        // From Education & Professional table
        if (cp.getEducationAndProfession() != null) {
            dto.setEducation(cp.getEducationAndProfession().getEducation());
            dto.setOccupation(cp.getEducationAndProfession().getOccupation());
        }

        // From ContactDetails table
        if (cp.getContactDetails() != null) {
            dto.setCity(cp.getContactDetails().getCity());
        }

        return dto;
    }
}
