package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> createContact(
            @RequestBody @Valid ContactDTO contactDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.createContactDetails(userId,contactDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getByUserId(){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = contactService.getContactDetails(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact details for userId : " +userId,response));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<ContactDetails>> updateByUserID(
            @RequestBody @Valid ContactDTO contactDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = contactService.updateContactDetails(userId,contactDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact details Updated Successfully !"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseDTO> deleteByUserID(){

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.deleteContactDetails(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }





}
