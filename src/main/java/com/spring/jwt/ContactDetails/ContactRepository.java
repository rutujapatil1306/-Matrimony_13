package com.spring.jwt.ContactDetails;

import com.spring.jwt.entity.ContactDetails;
import com.spring.jwt.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails,Integer> {


    Optional<ContactDetails> findByUserId(Integer userID);

    void deleteByUserId(Integer userID);

    boolean existsByMobileNumber(Long mobileNumber);
}
