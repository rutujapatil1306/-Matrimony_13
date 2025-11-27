package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
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
    public ResponseEntity<BaseResponseDTO> createContact(@RequestHeader("Authorization") String authHeader,
                                                         @RequestBody ContactDTO contactDTO) {

        Integer userId = SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = contactService.create(userId,contactDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<ApiResponse> getByUserId(@RequestParam Integer userId){

        ApiResponse response = contactService.getByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Retrived Data By Using User ID",response));
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<ContactDetails>> updateByUserID(@RequestParam Integer userId, @RequestBody ContactDTO contactDTO){
        ApiResponse response = contactService.updateByUserID(userId,contactDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Contact Updated Sucessfully !"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseDTO> deleteByUserID(@RequestParam Integer userID){
        BaseResponseDTO response = contactService.deleteByUserID(userID);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }





}
