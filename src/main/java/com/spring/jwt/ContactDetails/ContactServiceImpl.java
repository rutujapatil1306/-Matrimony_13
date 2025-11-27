package com.spring.jwt.ContactDetails;

import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.exception.ContactNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;

    private final CompleteProfileRepository completeProfileRepository;

    public ContactServiceImpl(ContactRepository contactRepository, CompleteProfileRepository completeProfileRepository){
        this.contactRepository = contactRepository;
        this.completeProfileRepository = completeProfileRepository;
    }


    @Override
    public ApiResponse updateByUserID(Integer userID, ContactDTO contactDTO) {

        ContactDetails existingContact = contactRepository.findByUserId(userID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for userID: " + userID +
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

    @Override
    public BaseResponseDTO create(ContactDTO contactDTO) {

        ContactDetails saveContact = ContactMapper.toEntity(contactDTO);
        contactRepository.save(saveContact);

        CompleteProfile completeProfile = new CompleteProfile();
        completeProfile.setContactDetails(saveContact);
        completeProfileRepository.save(completeProfile);

        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Contact Saved Successfully");
        response.setID(saveContact.getContactId());

        return response;
    }


    @Override
    public ContactDetails getContactById(Integer contactID) {

        return contactRepository.findById(contactID)
                .orElseThrow(() -> new ContactNotFoundException(
                        "Contact Details Not found with id " + contactID
                ));
    }

//    @Override
//    public Page getAll(int pageNumber, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        return (Page) contactRepository.findAll(pageable);
//    }


    @Override
    public ApiResponse updateByContactID(Integer contactID, ContactDTO contactDTO) {
        ContactDetails existingContact = contactRepository.findById(contactID)
                .orElseThrow(() -> new UserNotFoundExceptions(
                        "No contact details found for contact ID: " + contactID +
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


