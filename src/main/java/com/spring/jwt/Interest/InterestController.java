package com.spring.jwt.Interest;

import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {

    private final ExpressInterestService service;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<SendInterestRequestDTO>> sendInterest(
            @RequestBody @Valid SendInterestRequestDTO dto) {

        Integer userId = SecurityUtil.getCurrentUserId();
        service.sendInterest(userId, dto.getToUserId(), dto.getMessage());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Interest sent successfully"));
    }

    @PostMapping("/accept/{interestId}")
    public ResponseEntity<ApiResponse> accept(@PathVariable Long interestId) {

        Integer userId = SecurityUtil.getCurrentUserId();
        service.acceptInterest(userId, interestId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.success("Interest accepted"));
    }

    @PostMapping("/decline/{interestId}")
    public ResponseEntity<ApiResponse> decline(@PathVariable Long interestId) {

        Integer userId = SecurityUtil.getCurrentUserId();
        service.declineInterest(userId, interestId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Request declined"));
    }

    @GetMapping("/received")
    public ResponseEntity<ApiResponse<Page<InterestResponseDTO>>> getReceivedInterest(Pageable pageable) {

        Integer userId = SecurityUtil.getCurrentUserId();
        Page<InterestResponseDTO> receivedInterest = service.getReceived(userId, pageable);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Received interest record retrieved successfully", receivedInterest));
    }

    @GetMapping("/sent")
    public ResponseEntity<ApiResponse<Page<InterestResponseDTO>>> getSentInterest(Pageable pageable){

        Integer userId = SecurityUtil.getCurrentUserId();
        Page<InterestResponseDTO> sent = service.getSent(userId, pageable);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ApiResponse.success("Sent interest record retrieved successfully", sent));

    }
}
