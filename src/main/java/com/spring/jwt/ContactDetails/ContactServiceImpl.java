package com.spring.jwt.ContactDetails;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.UserProfile;
import com.spring.jwt.exception.ContactNotFoundException;
import com.spring.jwt.exception.UserAlreadyExistException;
import com.spring.jwt.exception.UserNotFoundExceptions;
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
    public BaseResponseDTO create(Integer userId, ContactDTO contactDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

//        Optional<ContactDetails> existing = contactRepository.findByUserId(userId);
//        if (existing != null) {
//            throw new UserAlreadyExistException("Contact already exists");
//        }

        ContactDetails saveContact = ContactMapper.toEntity(contactDTO);
        saveContact.setUser(user);
        contactRepository.save(saveContact);

        CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
        completeProfile.setContactDetails(saveContact);
        completeProfileRepository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Contact Saved Successfully");
        response.setID(saveContact.getContactId());

        return response;
    }

    @Override
    public ApiResponse getByUserId(Integer userId) {

        ContactDetails existingContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userId +
                                ". Please register first."
                ));


        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact updated successfully");
        response.setData(existingContact);

        return response;
    }

    @Override
    public ApiResponse updateByUserID(Integer userId, ContactDTO contactDTO) {

        ContactDetails existingContact = contactRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userId +
                                ". Please register first."
                ));

        updateContact(existingContact, contactDTO);
        ContactDetails savedContact = contactRepository.save(existingContact);
        ContactDTO responseDTO = ContactMapper.toDto(savedContact);

        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Contact updated successfully");
        response.setData(responseDTO);

        return response;
    }

    @Transactional
    @Override
    public BaseResponseDTO deleteByUserID(Integer userID) {
        ContactDetails existingContact = contactRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userID +
                                ". Please register first."
                ));

        contactRepository.deleteByUserId(userID);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("201");
        response.setMessage("Contact Deleted Successfully");


        return response;
    }

    private ContactDetails updateContact(ContactDetails existingContact, ContactDTO contactDTO) {
        if (contactDTO.getFullAddress() != null) {
            existingContact.setFullAddress(contactDTO.getFullAddress());
        }
        if (contactDTO.getCity() != null) {
            existingContact.setCity(contactDTO.getCity());
        }
        if (contactDTO.getPinCode() != null) {
            existingContact.setPinCode(contactDTO.getPinCode());
        }
        if (contactDTO.getMobileNumber() != null) {
            existingContact.setMobileNumber(contactDTO.getMobileNumber());
        }
        if (contactDTO.getAlternateNumber() != null) {
            existingContact.setAlternateNumber(contactDTO.getAlternateNumber());
        }

        return existingContact;
    }


}


