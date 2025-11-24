package com.spring.jwt.profile;

import com.spring.jwt.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserId(Integer userId);

}
