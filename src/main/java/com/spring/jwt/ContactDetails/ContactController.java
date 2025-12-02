package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseDTO> createContact(
            @RequestBody ContactDTO contactDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.create(userId,contactDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getByUserId(){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = contactService.getByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Retrieved Data By Using User ID",response));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<ContactDetails>> updateByUserID(@RequestBody ContactDTO contactDTO){

        Integer userId = SecurityUtil.getCurrentUserId();
        ApiResponse response = contactService.updateByUserID(userId,contactDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact Updated Successfully !"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseDTO> deleteByUserID(){

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.deleteByUserID(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }





}
