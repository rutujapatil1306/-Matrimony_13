package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> createContact(
            @RequestBody ContactDTO contactDTO) {

        Integer userId= SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.create(contactDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<ApiResponse<ContactDetails>> getContactByID(@RequestParam Integer contactID) {

        ContactDetails contact = contactService.getContactById(contactID);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Contact Details For Id " + contactID, contact));
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<ApiResponse<Page>> getAllContacts(
//            @RequestParam(defaultValue = "0") int page) {
//        int pageSize = 4;  // 4 contacts per page
//        Page contactDetails = contactService.getAll(page, pageSize);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(ApiResponse.success("List of contacts - Page " + page, contactDetails));
//    }


    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<ContactDetails>> updateByUserID(@RequestParam Integer userID, @RequestBody ContactDTO contactDTO){
        ApiResponse response = contactService.updateByUserID(userID,contactDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact Updated Sucessfully ! "));
    }

    @PatchMapping("/updateByContactID")
    public ResponseEntity<ApiResponse<ContactDetails>> updateByContactID(@RequestParam Integer contactID , @RequestBody ContactDTO contactDTO){

        ApiResponse response = contactService.updateByContactID(contactID,contactDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact Updated Sucessfully ! "));
    }



}
