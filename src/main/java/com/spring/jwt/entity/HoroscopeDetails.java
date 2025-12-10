package com.spring.jwt.entity;

import com.spring.jwt.Enums.Status;
import jakarta.persistence.*;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "horoscope_details")
@Getter
@Setter
public class HoroscopeDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer horoscopeDetailsId;

    @Column(nullable = false)
    private Date dob;

    @Column(length = 45, nullable = false)
    private String time;

    @Column(length = 45, nullable = false)
    private String birthPlace;

    @Column(length = 45, nullable = false)
    private String rashi;

    @Column(length = 45, nullable = false)
    private String nakshatra;

    @Column(length = 45, nullable = false)
    private String charan;

    @Column(length = 45, nullable = false)
    private String nadi;

    @Column(length = 45, nullable = false)
    private String gan;

    @Column(length = 45, nullable = false)
    private String mangal;

    @Column(length = 45,nullable = false)
    private String gotra;

    @Column(length = 45, nullable = false)
    private String devak;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "horoscopeDetails")
    private CompleteProfile completeProfile;

}