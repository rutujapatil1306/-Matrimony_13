package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import org.hibernate.query.Page;

import java.util.List;

public interface ContactService {

    BaseResponseDTO create(Integer userId,ContactDTO contactDTO);

    ApiResponse getByUserId(Integer userId);

    ApiResponse updateByUserID(Integer userId, ContactDTO contactDTO);

    BaseResponseDTO deleteByUserID(Integer userID);


}
