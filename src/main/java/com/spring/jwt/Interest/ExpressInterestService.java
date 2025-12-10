package com.spring.jwt.Interest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExpressInterestService {
    void sendInterest(Integer fromUserId, Integer toUserId, String message);
    void acceptInterest(Integer userId, Long interestId);
    void declineInterest(Integer userId, Long interestId);
    Page<InterestResponseDTO> getReceivedInterests(Integer userId, Pageable pageable);
    Page<InterestResponseDTO> getSentInterests(Integer userId, Pageable pageable);
    Page<InterestResponseDTO> getPendingReceived(Integer userId, Pageable pageable);
    Page<InterestResponseDTO> getPendingSent(Integer userId, Pageable pageable);
}
