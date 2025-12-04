package com.spring.jwt.Interest;

import com.spring.jwt.entity.InterestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterestResponseDTO {

    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private InterestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime respondedAt;

}
