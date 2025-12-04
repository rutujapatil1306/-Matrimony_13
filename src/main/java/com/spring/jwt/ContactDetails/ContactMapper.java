package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public static ContactDetails toEntity(ContactDTO dto) {
        if (dto == null) return null;

        ContactDetails contact = new ContactDetails();

        if (dto.getFullAddress() != null && !dto.getFullAddress().isEmpty()) {
            contact.setFullAddress(dto.getFullAddress());
        }
        if (dto.getCity() != null && !dto.getCity().isEmpty()) {
            contact.setCity(dto.getCity());
        }
        if (dto.getPinCode() != null) {
            contact.setPinCode(dto.getPinCode());
        }
        if (dto.getMobileNumber() != null) {
            contact.setMobileNumber(dto.getMobileNumber());
        }
        if (dto.getAlternateNumber() != null) {
            contact.setAlternateNumber(dto.getAlternateNumber());
        }

        return contact;
    }

    // ------------------------ TO DTO -----------------------------
    public static ContactDTO toDTO(ContactDetails contact) {
        if (contact == null) return null;

        ContactDTO dto = new ContactDTO();


        if (contact.getFullAddress() != null) {
            dto.setFullAddress(contact.getFullAddress());
        }
        if (contact.getCity() != null) {
            dto.setCity(contact.getCity());
        }
        if (contact.getPinCode() != null) {
            dto.setPinCode(contact.getPinCode());
        }
        if (contact.getMobileNumber() != null) {
            dto.setMobileNumber(contact.getMobileNumber());
        }
        if (contact.getAlternateNumber() != null) {
            dto.setAlternateNumber(contact.getAlternateNumber());
        }

        return dto;
    }
}
