package com.spring.jwt.entity;

import com.spring.jwt.Enums.Gender;
import com.spring.jwt.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userProfileId;

    @Column(length = 45, nullable = false)
    private String firstName;

    @Column(length = 45, nullable = false)
    private String middleName;

    @Column(length = 45, nullable = false)
    private String lastName;

    @Column(length = 250, nullable = false )
    private String address;

    @Column(length = 45, nullable = false )
    private String taluka;

    @Column(length = 45, nullable = false )
    private String district;

    @Column(nullable = false)
    private Integer pinCode;

    @Column(nullable = false, unique = true, length = 10)
    private String mobileNumber;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DEACTIVE;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 45, nullable = false )
    private String religion;

    @Column(length = 45, nullable = false )
    private String caste;

    @Column(length = 45, nullable = false )
    private String maritalStatus;

    @Column(nullable = false )
    private Double height;

    @Column(nullable = false )
    private Integer weight;

    @Column(length = 45, nullable = false )
    private String bloodGroup;

    @Column(length = 45, nullable = false )
    private String complexion;

    @Column(length = 45, nullable = false )
    private String diet;

    @Column(nullable = false )
    private Boolean spectacle;

    @Column(nullable = false )
    private Boolean lens;

    @Column(nullable = false )
    private Boolean physicallyChallenged;

    @Column(length = 45, nullable = false )
    private String homeTownDistrict;

    @Column(length = 45, nullable = false )
    private String nativeTaluka;

    @Column(length = 45, nullable = false )
    private String currentCity;

    private String userProfileCol;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "userProfile")
    private CompleteProfile completeProfile;
}
