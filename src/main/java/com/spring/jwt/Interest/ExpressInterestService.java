package com.spring.jwt.Interest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExpressInterestService {
    void sendInterest(Integer fromUserId, Integer toUserId, String message);
    void acceptInterest(Integer userId, Long interestId);
    void declineInterest(Integer userId, Long interestId);
    Page<InterestResponseDTO> getReceived(Integer userId, Pageable pageable);
    Page<InterestResponseDTO> getSent(Integer userId, Pageable pageable);
    Page<InterestResponseDTO> getPending(Integer userId, Pageable pageable);
}
