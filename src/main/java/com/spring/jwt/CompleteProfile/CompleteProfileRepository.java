package com.spring.jwt.CompleteProfile;

import com.spring.jwt.entity.CompleteProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompleteProfileRepository extends JpaRepository<CompleteProfile, Integer> {

    CompleteProfile findByUserId(Integer userId);

    @Query("""
    select cp from CompleteProfile cp
    left join fetch cp.userProfile
    left join fetch cp.educationAndProfession
    left join fetch cp.horoscopeDetails
    left join fetch cp.familyBackground
    left join fetch cp.contactDetails
    left join fetch cp.partnerPreference
    left join fetch cp.document
    where cp.user.id = :userId
""")
    Optional<CompleteProfile> findFullProfileByUserId(Integer userId);

}
