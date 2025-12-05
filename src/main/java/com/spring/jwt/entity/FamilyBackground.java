package com.spring.jwt.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "family_background")
@Getter
@Setter
public class FamilyBackground {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer familyBackgroundId;

    @Column(length = 45)
    private String fathersName;

    @Column(length = 45)
    private String mothersName;

    @Column(length = 45)
    private String fatherOccupation;

    @Column(length = 45)
    private String motherOccupation;

    @Column(length = 45)
    private Integer brother;

    @Column(length = 45)
    private Integer marriedBrothers;

    @Column(length = 45)
    private Integer sisters;

    @Column(length = 45)
    private Integer marriedSisters;

    @Column(length = 45)
    private Boolean interCasteInFamily;

    @Column(length = 45)
    private String parentResiding;

    @Column(length = 45)
    private String familyWealth;

    @Column(length = 45)
    private String mamaSurname;

    @Column(length = 45)
    private String mamaPlace;

    @Column(length = 45)
    private String familyBackgroundCol;

    @Column(length = 45)
    private String relativeSurnames;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "familyBackground")
    private CompleteProfile status;

}