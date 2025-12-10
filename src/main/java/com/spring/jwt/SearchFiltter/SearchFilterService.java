package com.spring.jwt.SearchFiltter;

import com.spring.jwt.dto.PublicProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchFilterService {


    Page<PublicProfileDTO> searchProfiles(SearchFilterDTO filter, Pageable pageable, Integer userId);
}
