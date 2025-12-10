package com.spring.jwt.HoroscopeDetails;

import com.spring.jwt.entity.HoroscopeDetails;
import com.spring.jwt.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface HoroscopeDetailsRepository extends JpaRepository<HoroscopeDetails, Integer> {

    @Query("SELECT p FROM HoroscopeDetails p WHERE p.user.id = :userId")
    Optional<HoroscopeDetails> findByUserId(@Param("userId") Integer userId);
}
