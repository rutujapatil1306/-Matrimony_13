package com.spring.jwt.FamilyBackground;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.FamilyBackgroundNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyBackgroundServiceImpl implements FamilyBackgroundService{

    private final FamilyBackgroundRepository repository;
    private final UserRepository userRepository;
    private final FamilyBackgroundMapper mapper;
    private final CompleteProfileRepository completeProfileRepository;

    @Override
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
    public FamilyBackgroundDTO getBackground(Integer userId) {
        return repository.findByUserId(userId).map(mapper::toDTO)
                .orElseThrow(()-> new FamilyBackgroundNotFoundException("Family Background Not Found"));
    }

    @Override
    public ApiResponse updateBackground(Integer userId, FamilyBackgroundDTO dto) {
        FamilyBackground background = repository.findByUserId(userId)
                .orElseThrow(() -> new FamilyBackgroundNotFoundException("FamilyBackground not found"));

        if (dto.getFatherOccupation() != null) {
            background.setFatherOccupation(dto.getFatherOccupation());
        }
        if (dto.getMotherOccupation() != null) {
            background.setMotherOccupation(dto.getMotherOccupation());
        }
        if (dto.getBrothers() != null) {
            background.setBrother(dto.getBrothers());
        }
        if (dto.getMarriedBrothers() != null) {
            background.setMarriedBrothers(dto.getMarriedBrothers());
        }
        if (dto.getSisters() != null) {
            background.setSisters(dto.getSisters());
        }
        if (dto.getMarriedSisters() != null) {
            background.setMarriedSisters(dto.getMarriedSisters());
        }
        if (dto.getInterCasteInFamily() != null) {
            background.setInterCasteInFamily(dto.getInterCasteInFamily());
        }
        if (dto.getParentResiding() != null) {
            background.setParentResiding(dto.getParentResiding());
        }
        if (dto.getMamaPlace() != null) {
            background.setMamaPlace(dto.getMamaPlace());
        }
        if (dto.getMamaSurname() != null) {
            background.setMamaSurname(dto.getMamaSurname());
        }
        if (dto.getFamilyBackgroundCol() != null) {
            background.setFamilyBackgroundCol(dto.getFamilyBackgroundCol());
        }
        if (dto.getRelativeSurnames() != null) {
            background.setRelativeSurnames(dto.getRelativeSurnames());
        }
        FamilyBackground savedBackground= repository.save(background);
        FamilyBackgroundDTO responseDTO = mapper.toDTO(savedBackground);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact updated successfully");
        response.setData(responseDTO);

        return response;
    }
}
