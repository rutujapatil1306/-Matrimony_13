package com.spring.jwt.PartnerPreference;

import com.spring.jwt.ContactDetails.ContactMapper;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.PartnerPreference;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerPreferenceServiceImpl implements PartnerPreferenceService{

   private final PartnerPreferenceRepository partnerPreferenceRepository;


    @Override
    public BaseResponseDTO create(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO) {

        PartnerPreference saveContact = PartnerPreferenceMapper.toEntity(partnerPreferenceDTO);
        partnerPreferenceRepository.save(saveContact);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Partner Preference Saved Successfully");
        response.setID(saveContact.getPartnerPreferenceId());  // set userID here

        return response;
    }
}
