package com.spring.jwt.EducationAndProfession;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.HoroscopeDetails.HelperUtil;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.EducationAndProfession;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.DuplicateResourceException;
import com.spring.jwt.exception.EducationNotFoundException;
import com.spring.jwt.exception.ResourceNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

        // PREVENT DUPLICATE BEFORE SAVE (BEST)
        if (educationRepository.existsByUserId(userId)) {
            throw new DuplicateResourceException(
                    "Education and profession details already exist for this user"
            );
        }

        EducationAndProfession educationAndProfession = mapper.toEntity(educationDTO);
        educationAndProfession.setUser(user);
        educationRepository.save(educationAndProfession);

        CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
        completeProfile.setEducationAndProfession(educationAndProfession);
        completeProfileRepository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Education and profession details Saved Successfully");
        response.setID(educationAndProfession.getEducationId());

        return response;
    }


    @Transactional
    @Override
    public ApiResponse updateEducationAndProfession(Integer userID, EducationDTO educationDTO) {

        EducationAndProfession existing = educationRepository.findByUserId(userID)
                .orElseThrow(() -> new ResourceNotFoundException("Education and profession details not found"));

        HelperUtil.getDataIfNotNull(educationDTO::getEducation, existing::setEducation);
        HelperUtil.getDataIfNotNull(educationDTO::getDegree, existing::setDegree);
        HelperUtil.getDataIfNotNull(educationDTO::getOccupation, existing::setOccupation);
        HelperUtil.getDataIfNotNull(educationDTO::getOccupationDetails, existing::setOccupationDetails);
        HelperUtil.getDataIfNotNull(educationDTO::getIncomePerYear, existing::setIncomePerYear);


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
                .orElseThrow(()-> new ResourceNotFoundException
                        ("education and profession details not found for userId"));
    }

    @Transactional
    @Override
    public BaseResponseDTO deleteEducationDetails(Integer userID) {

        EducationAndProfession educationDetails = educationRepository.findByUserId(userID)
                .orElseThrow(() -> new ResourceNotFoundException("education details not found"));

        educationRepository.delete(educationDetails);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("education details deleted Successfully");

        return response;
    }

}
