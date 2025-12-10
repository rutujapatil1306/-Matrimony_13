package com.spring.jwt.PartnerPreference;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.PartnerPreference;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.PartnerPreferenceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartnerPreferenceServiceImpl implements PartnerPreferenceService{

   private final PartnerPreferenceRepository partnerPreferenceRepository;
   private final CompleteProfileRepository completeProfileRepository;
   private final UserRepository userRepository;
   private final PartnerPreferenceMapper mapper;


    @Override
    @Transactional
    public BaseResponseDTO createPreference(Integer userId, PartnerPreferenceDTO partnerPreferenceDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        PartnerPreference savePartner = mapper.toEntity(partnerPreferenceDTO);
        savePartner.setUser(user);
        partnerPreferenceRepository.save(savePartner);

        CompleteProfile completeProfile =  completeProfileRepository.findByUserId(userId);
        completeProfile.setPartnerPreference(savePartner);
        completeProfileRepository.save(completeProfile);


        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Partner preference saved successfully");
        response.setID(savePartner.getPartnerPreferenceId());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerPreferenceDTO getPreference(Integer userId) {
        return partnerPreferenceRepository.findByUserId(userId).map(mapper::toDTO)
                .orElseThrow(()-> new PartnerPreferenceNotFoundException("partner preference not found for userId : "+ userId));
    }

    @Override
    @Transactional
    public ApiResponse updatePreference(Integer userId, PartnerPreferenceDTO dto) {
        PartnerPreference partnerPreference = partnerPreferenceRepository.findByUserId(userId)
                .orElseThrow(() -> new PartnerPreferenceNotFoundException("partner preference not found for userId : "+ userId));

        if (dto.getPartnerAge() != null) {
            partnerPreference.setAge(dto.getPartnerAge());
        }
        if (dto.getPartnerCaste() != null) {
            partnerPreference.setCaste(dto.getPartnerCaste());
        }
        if (dto.getLookingFor() != null) {
            partnerPreference.setLookingFor(dto.getLookingFor());
        }
        if (dto.getPartnerHeight() != null) {
            partnerPreference.setHeight(dto.getPartnerHeight());
        }
        if (dto.getEatingHabits() != null) {
            partnerPreference.setEatingHabits(dto.getEatingHabits());
        }
        if (dto.getCountryLivingIn() != null) {
            partnerPreference.setCountryLivingIn(dto.getCountryLivingIn());
        }
        if (dto.getCityLivingIn() != null) {
            partnerPreference.setCityLivingIn(dto.getCityLivingIn());
        }
        if (dto.getPartnerComplexion() != null) {
            partnerPreference.setComplexion(dto.getPartnerComplexion());
        }
        if (dto.getPartnerReligion() != null) {
            partnerPreference.setReligion(dto.getPartnerReligion());
        }
        if (dto.getPartnerEducation() != null) {
            partnerPreference.setEducation(dto.getPartnerEducation());
        }
        if (dto.getMangal() != null) {
            partnerPreference.setMangal(dto.getMangal());
        }
        if (dto.getPartnerResidentStatus() != null) {
            partnerPreference.setResidentStatus(dto.getPartnerResidentStatus());
        }
        if (dto.getPartnerOccupation() != null) {
            partnerPreference.setPartnerOccupation(dto.getPartnerOccupation());
        }
        if (dto.getPartnerIncome() != null) {
            partnerPreference.setPartnerIncome(dto.getPartnerIncome());
        }

        PartnerPreference savedPreference = partnerPreferenceRepository.save(partnerPreference);
        PartnerPreferenceDTO responseDTO= mapper.toDTO(savedPreference);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Partner preference updated successfully");
        response.setData(responseDTO);

        return response;
    }
}
