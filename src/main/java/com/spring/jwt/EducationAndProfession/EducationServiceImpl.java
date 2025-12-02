package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final CompleteProfileRepository completeProfileRepository;



    @Override
    public BaseResponseDTO create(Integer userId ,EducationDTO educationDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        EducationAndProfession save = EducationMapper.toEntity(educationDTO);
        save.setUser(user);
        educationRepository.save(save);

        CompleteProfile completeProfile1 =  completeProfileRepository.findByUserId(userId);
        completeProfile1.setEducationAndProfession(save);
        completeProfileRepository.save(completeProfile1);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Education Saved Successfully");
        response.setID(save.getEducationId());

        return response;
    }

    @Override
    public ApiResponse updateByUserdID(Integer userID, EducationDTO educationDTO) {

        EducationAndProfession existing = educationRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userID +
                                ". Please register first."));

        updateEducation(existing,educationDTO);

        EducationAndProfession savedEducation = educationRepository.save(existing);

        EducationDTO responseDTO = EducationMapper.toDTO(savedEducation);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Education and Profession updated successfully");
        response.setData(responseDTO);

        return response;
    }


    private void updateEducation(EducationAndProfession existing, EducationDTO educationDTO) {
        if (educationDTO.getEducation() != null) {
            existing.setEducation(educationDTO.getEducation());
        }
        if (educationDTO.getDegree() != null) {
            existing.setDegree(educationDTO.getDegree());
        }
        if (educationDTO.getOccupation() != null) {
            existing.setOccupation(educationDTO.getOccupation());
        }
        if (educationDTO.getOccupationDetails() != null) {
            existing.setOccupationDetails(educationDTO.getOccupationDetails());
        }
        if (educationDTO.getIncomePerYear() != null){
            existing.setIncomePerYear(educationDTO.getIncomePerYear());
        }
//        if (educationDTO.getCompleteProfile()!=null){
//            existing.setCompleteProfile("Complete");
//        }
    }


}
