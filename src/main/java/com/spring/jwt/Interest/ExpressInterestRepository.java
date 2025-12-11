package com.spring.jwt.Interest;


import com.spring.jwt.entity.ExpressInterest;
import com.spring.jwt.Enums.InterestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ExpressInterestRepository extends JpaRepository<ExpressInterest, Long> {

    Page<ExpressInterest> findByToUserId(Integer userId, Pageable pageable);
    Page<ExpressInterest> findByFromUserId(Integer userId, Pageable pageable);
    boolean existsByFromUserIdAndToUserId(Integer fromUserId, Integer toUserId);

    Page<ExpressInterest> findByToUserIdAndStatus(Integer toUserId, InterestStatus status, Pageable pageable);
    Page<ExpressInterest> findByFromUserIdAndStatus(Integer fromUserId, InterestStatus status, Pageable pageable);

}
