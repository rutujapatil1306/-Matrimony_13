package com.spring.jwt.FamilyBackground;

import com.spring.jwt.entity.FamilyBackground;
import com.spring.jwt.entity.HoroscopeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FamilyBackgroundRepository extends JpaRepository<FamilyBackground, Integer> {

    @Query("SELECT p FROM FamilyBackground p WHERE p.user.id = :userId")
    Optional<FamilyBackground> findByUserId(@Param("userId") Integer userId);
}
