package com.spring.jwt.PartnerPreference;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.PartnerPreference;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerPreferenceServiceImpl implements PartnerPreferenceService{

   private final PartnerPreferenceRepository partnerPreferenceRepository;
   private final CompleteProfileRepository completeProfileRepository;
   private final UserRepository userRepository;


    @Override
    public BaseResponseDTO create(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        PartnerPreference savePartner = PartnerPreferenceMapper.toEntity(partnerPreferenceDTO);
        savePartner.setUser(user);
        partnerPreferenceRepository.save(savePartner);

        CompleteProfile completeProfile =  completeProfileRepository.findByUserId(userId);
        completeProfile.setPartnerPreference(savePartner);
        completeProfileRepository.save(completeProfile);


        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Partner Preference Saved Successfully");
        response.setID(savePartner.getPartnerPreferenceId());  // set userID here

        return response;
    }
}
