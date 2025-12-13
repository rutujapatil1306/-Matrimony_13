package com.spring.jwt.PartnerPreference;

import com.spring.jwt.entity.PartnerPreference;
import io.github.bucket4j.VerboseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerPreferenceRepository extends JpaRepository<PartnerPreference,Integer> {

    Optional<PartnerPreference> findByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}
