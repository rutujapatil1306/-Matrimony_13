package com.spring.jwt.Interest;


import com.spring.jwt.entity.ExpressInterest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ExpressInterestRepository extends JpaRepository<ExpressInterest, Long> {

    Page<ExpressInterest> findByToUserId(Integer userId, Pageable pageable);    // received
    Page<ExpressInterest> findByFromUserId(Integer userId, Pageable pageable);  // sent

    boolean existsByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId);
}
