package com.spring.jwt.Interest;

import com.spring.jwt.Enums.InterestStatus;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/v1/interests")
@RequiredArgsConstructor
public class InterestController {

    private final ExpressInterestService service;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Void>> sendInterest(
            @RequestParam @NotNull Integer toUserId) {

        Integer userId = SecurityUtil.getCurrentUserId();
        service.sendInterest(userId, toUserId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Interest sent successfully"));
    }

    @PutMapping("/accept/{interestId}")
    public ResponseEntity<ApiResponse<Void>> acceptInterest(@PathVariable Long interestId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        service.acceptInterest(userId, interestId);
        return ResponseEntity.ok(ApiResponse.success("Interest accepted successfully"));
    }

    @PutMapping("/decline/{interestId}")
    public ResponseEntity<ApiResponse<Void>> declineInterest(@PathVariable Long interestId) {
        Integer userId = SecurityUtil.getCurrentUserId();
        service.declineInterest(userId, interestId);
        return ResponseEntity.ok(ApiResponse.success("Interest declined successfully"));
    }


    @GetMapping("/received")
    public ResponseEntity<ApiResponse<Page<InterestResponseDTO>>> getReceivedInterest(
            @RequestParam InterestStatus status,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 30, message = "Page size cannot exceed 30") int size){

        Integer userId = SecurityUtil.getCurrentUserId();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<InterestResponseDTO> receivedInterest = service.getReceivedInterests(userId,status, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Received interest record retrieved successfully", receivedInterest));
    }

    @GetMapping("/sent")
    public ResponseEntity<ApiResponse<Page<InterestResponseDTO>>> getSentInterest(
            @RequestParam InterestStatus status,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
            @Max(value = 30, message = "Page size cannot exceed 30") int size){

        Integer userId = SecurityUtil.getCurrentUserId();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<InterestResponseDTO> sent = service.getSentInterests(userId,status,pageable);
        return ResponseEntity.ok(
                ApiResponse.success("Sent interest record retrieved successfully", sent));

    }

//    @GetMapping("/pending/inReceived")
//    public Page<InterestResponseDTO> getPendingReceived(
//            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
//            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
//            @Max(value = 30, message = "Page size cannot exceed 30") int size) {
//
//        Integer userId = SecurityUtil.getCurrentUserId();
//        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//
//        return service.getPendingReceived(userId, pageable);
//    }
//
//    @GetMapping("/pending/inSent")
//    public Page<InterestResponseDTO> getPendingSent(
//            @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number cannot be negative") int page,
//            @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size must be at least 1")
//            @Max(value = 30, message = "Page size cannot exceed 30") int size) {
//
//        Integer userId = SecurityUtil.getCurrentUserId();
//        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
//
//        return service.getPendingSent(userId, pageable);
    }


