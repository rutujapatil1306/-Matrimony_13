package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.EducationNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final CompleteProfileRepository completeProfileRepository;
    private final EducationMapper mapper;


    @Transactional
    @Override
    public BaseResponseDTO createEducationAndProfession(Integer userId, EducationDTO educationDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        EducationAndProfession save = mapper.toEntity(educationDTO);
        save.setUser(user);
        educationRepository.save(save);

        CompleteProfile completeProfile1 = completeProfileRepository.findByUserId(userId);
        completeProfile1.setEducationAndProfession(save);
        completeProfileRepository.save(completeProfile1);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Education and profession details Saved Successfully");
        response.setID(save.getEducationId());

        return response;
    }

    @Transactional
    @Override
    public ApiResponse updateEducationAndProfession(Integer userID, EducationDTO educationDTO) {

        EducationAndProfession existing = educationRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userID +
                                ". Please register first."));

        updateEducation(existing, educationDTO);

        EducationAndProfession savedEducation = educationRepository.save(existing);
        EducationDTO responseDTO = mapper.toDTO(savedEducation);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Education and Profession details updated successfully");
        response.setData(responseDTO);

        return response;
    }

    @Transactional(readOnly = true)
    @Override
    public EducationDTO getEducationAndProfession(Integer userId) {
        return educationRepository.findByUserId(userId).map(mapper::toDTO)
                .orElseThrow(() -> new EducationNotFoundException
                        ("education and profession details not found for userId :" + userId));
    }


    private void updateEducation(EducationAndProfession existing, EducationDTO educationDTO) {
        if (educationDTO.getEducation() != null) {
            existing.setEducation(educationDTO.getEducation());

        }
    }
}




