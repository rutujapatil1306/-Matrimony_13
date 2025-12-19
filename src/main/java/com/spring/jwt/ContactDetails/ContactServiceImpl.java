package com.spring.jwt.ContactDetails;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.HoroscopeDetails.HelperUtil;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;
    private final CompleteProfileRepository completeProfileRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponseDTO createContactDetails(Integer userId, ContactDTO contactDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (contactRepository.existsByUserId(userId)) {
            throw new DuplicateResourceException(
                    "Contact details already exist for this user"
            );
        }

        if (contactRepository.existsByMobileNumber(contactDTO.getMobileNumber())) {
            throw new UserAlreadyExistException("Mobile number already exists");
        }

        ContactDetails saveContact = ContactMapper.toEntity(contactDTO);
        saveContact.setUser(user);
        contactRepository.save(saveContact);

        CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
        completeProfile.setContactDetails(saveContact);
        completeProfileRepository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Contact details Saved Successfully");
        response.setID(saveContact.getContactId());

        return response;
    }

    @Override
    public ApiResponse getContactDetails(Integer userId) {

        ContactDetails existingContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Details not found"));

        ContactDTO dto =  ContactMapper.toDTO(existingContact);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact details retrieved successfully");
        response.setData(dto);

        return response;
    }

    @Transactional
    @Override
    public ApiResponse updateContactDetails(Integer userId, ContactDTO contactDTO) {

        ContactDetails existingContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact details not found"));

        if (contactRepository.existsByMobileNumber(contactDTO.getMobileNumber())) {
            throw new UserAlreadyExistException("Mobile number already exists");
        }

        HelperUtil.getDataIfNotNull(contactDTO::getFullAddress, existingContact::setFullAddress);
        HelperUtil.getDataIfNotNull(contactDTO::getCity, existingContact::setCity);
        HelperUtil.getDataIfNotNull(contactDTO::getPinCode, existingContact::setPinCode);
        HelperUtil.getDataIfNotNull(contactDTO::getMobileNumber, existingContact::setMobileNumber);
        HelperUtil.getDataIfNotNull(contactDTO::getAlternateNumber, existingContact::setAlternateNumber);

        ContactDetails savedContact = contactRepository.save(existingContact);
        ContactDTO responseDTO = ContactMapper.toDTO(savedContact);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact details updated successfully");
        response.setData(responseDTO);

        return response;
    }

    @Transactional
    @Override
    public BaseResponseDTO deleteContactDetails(Integer userID) {

        ContactDetails contactDetails = contactRepository.findByUserId(userID)
                .orElseThrow(() -> new ResourceNotFoundException("Contact details not found"));

        contactRepository.delete(contactDetails);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Contact details deleted Successfully");

        return response;
    }


}


