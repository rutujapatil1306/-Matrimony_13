package com.spring.jwt.FamilyBackground;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.HoroscopeDetails.HelperUtil;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.FamilyBackgroundNotFoundException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyBackgroundServiceImpl implements FamilyBackgroundService{

    private final FamilyBackgroundRepository repository;
    private final UserRepository userRepository;
    private final FamilyBackgroundMapper mapper;
    private final CompleteProfileRepository completeProfileRepository;

    @Override
    @Transactional
    public BaseResponseDTO saveFamilyBackground(Integer userId, FamilyBackgroundDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        FamilyBackground entity = mapper.toEntity(dto);
        entity.setUser(user);
        repository.save(entity);

        CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
        completeProfile.setFamilyBackground(entity);
        completeProfileRepository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("FamilyBackground saved successfully");
        response.setID(entity.getFamilyBackgroundId());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public FamilyBackgroundDTO getBackground(Integer userId) {
        return repository.findByUserId(userId).map(mapper::toDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Family Background Not Found"));
    }

    @Override
    @Transactional
    public ApiResponse updateBackground(Integer userId, FamilyBackgroundDTO dto) {
        FamilyBackground background = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Family Background not found"));

        HelperUtil.getDataIfNotNull(dto::getFatherOccupation, background::setFatherOccupation);
        HelperUtil.getDataIfNotNull(dto::getMotherOccupation, background::setMotherOccupation);
        HelperUtil.getDataIfNotNull(dto::getBrothers, background::setBrother);
        HelperUtil.getDataIfNotNull(dto::getMarriedBrothers, background::setMarriedBrothers);
        HelperUtil.getDataIfNotNull(dto::getSisters, background::setSisters);
        HelperUtil.getDataIfNotNull(dto::getMarriedSisters, background::setMarriedSisters);
        HelperUtil.getDataIfNotNull(dto::getInterCasteInFamily, background::setInterCasteInFamily);
        HelperUtil.getDataIfNotNull(dto::getParentResiding, background::setParentResiding);
        HelperUtil.getDataIfNotNull(dto::getMamaPlace, background::setMamaPlace);
        HelperUtil.getDataIfNotNull(dto::getMamaSurname, background::setMamaSurname);
        HelperUtil.getDataIfNotNull(dto::getFamilyBackgroundCol, background::setFamilyBackgroundCol);
        HelperUtil.getDataIfNotNull(dto::getRelativeSurnames, background::setRelativeSurnames);

        FamilyBackground savedBackground= repository.save(background);
        FamilyBackgroundDTO responseDTO = mapper.toDTO(savedBackground);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Family background details updated successfully");
        response.setData(responseDTO);

        return response;
    }
}
