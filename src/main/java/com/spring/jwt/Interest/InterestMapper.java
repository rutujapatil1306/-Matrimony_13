package com.spring.jwt.Interest;

import com.spring.jwt.HoroscopeDetails.HoroscopeDTO;
import com.spring.jwt.entity.ExpressInterest;
import com.spring.jwt.entity.HoroscopeDetails;
import org.springframework.stereotype.Component;

@Component
public class InterestMapper {

    public InterestResponseDTO toDTO(ExpressInterest i) {

        if (i == null){
            return null;
        }

        InterestResponseDTO dto = new InterestResponseDTO();

        dto.setFromUserId(i.getFromUser().getId());
        dto.setToUserId(i.getToUser().getId());
        dto.setMessage(i.getMessage());
        dto.setStatus(i.getStatus());
        dto.setCreatedAt(i.getCreatedAt());
        dto.setRespondedAt(i.getRespondedAt());

        return dto;
    }

}
