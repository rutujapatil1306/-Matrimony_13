package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "express_interest")
@Data
public class ExpressInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    // who sent
    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;

    // who received
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestStatus status;  // SENT, ACCEPTED, DECLINED, WITHDRAWN

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime respondedAt;
}
