package com.spring.jwt.Interest;

import com.spring.jwt.Enums.InterestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ExpressInterestService {
    void sendInterest(Integer fromUserId, Integer toUserId);

    InterestResponseDTO acceptInterest(Integer userId, Long interestId);

    InterestResponseDTO declineInterest(Integer userId, Long interestId);

    Page<InterestResponseDTO> getReceivedInterests(Integer userId, InterestStatus status, Pageable pageable);

    Page<InterestResponseDTO> getSentInterests(Integer userId, InterestStatus status, Pageable pageable);
}
