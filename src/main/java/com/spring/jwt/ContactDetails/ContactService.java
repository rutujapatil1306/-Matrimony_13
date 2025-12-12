package com.spring.jwt.ContactDetails;


import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;



public interface ContactService {

    BaseResponseDTO createContactDetails(Integer userId,ContactDTO contactDTO);
    ApiResponse getContactDetails(Integer userId);
    ApiResponse updateContactDetails(Integer userId, ContactDTO contactDTO);
    BaseResponseDTO deleteContactDetails(Integer userID);


}
