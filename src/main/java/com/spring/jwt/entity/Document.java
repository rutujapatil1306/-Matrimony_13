package com.spring.jwt.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "document")
@Getter
@Setter
public class Document {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;

    @Column(length = 45, nullable = false)
    private String documentName;

    @Lob
    @Column(name = "document_data", nullable = false)
    private byte[] documentFile;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(length = 45)
//    private String status1;
//
//    @OneToOne(mappedBy = "document")
//    private CompleteProfile status;

}