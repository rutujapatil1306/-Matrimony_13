package com.spring.jwt.EducationAndProfession;


import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class EducationAndProfessionServiceImpl implements EducationAndProfessionService{

    private final EducationAndProfessionRepository educationAndProfessionRepository;

    public EducationAndProfessionServiceImpl(EducationAndProfessionRepository educationAndProfessionRepository) {
        this.educationAndProfessionRepository = educationAndProfessionRepository;
    }


    @Override
    public ApiResponse save(Integer userID, EducationDTO educationDTO) {

        EducationAndProfession existing = educationAndProfessionRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userID +
                          ". Please register first."));

        updateEducation(existing,educationDTO);

        EducationAndProfession savedEducation = educationAndProfessionRepository.save(existing);

        EducationDTO responseDTO = EducationMapper.toDTO(savedEducation);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Education and Profession updated successfully");
        response.setData(responseDTO);

        return response;
    }

    @Override
    public BaseResponseDTO create(EducationDTO educationDTO) {

        EducationAndProfession save = EducationMapper.toEntity(educationDTO);
        educationAndProfessionRepository.save(save);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Education Saved Successfully");
        response.setID(save.getEducationId());  // set userID here

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
