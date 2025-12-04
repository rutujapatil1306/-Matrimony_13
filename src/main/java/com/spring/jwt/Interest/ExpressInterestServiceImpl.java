package com.spring.jwt.Interest;

import com.spring.jwt.entity.ExpressInterest;
import com.spring.jwt.entity.InterestStatus;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;



@Service
@RequiredArgsConstructor
public class ExpressInterestServiceImpl implements ExpressInterestService {

    private final ExpressInterestRepository interestRepository;
    private final UserRepository userRepository;
    private final InterestMapper mapper;

    // ================================================================
    // SEND INTEREST
    // ================================================================
    @Override
    public void sendInterest(Integer fromUserId, Integer toUserId, String message) {

        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("You cannot send interest to yourself.");
        }

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("Sender user not found"));

        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Receiver user not found"));

        // -------------------------------------------------------------
        // 1️⃣ Gender check (MOST IMPORTANT)
        // -------------------------------------------------------------
        String senderGender = fromUser.getGender();
        String receiverGender = toUser.getGender();

        if (senderGender.equalsIgnoreCase(receiverGender)) {
            throw new IllegalStateException("You can send interest only to opposite gender.");
        }

        // Optional: Allow only Male → Female or Female → Male
        if (
                !(senderGender.equalsIgnoreCase("Male") && receiverGender.equalsIgnoreCase("Female")) &&
                        !(senderGender.equalsIgnoreCase("Female") && receiverGender.equalsIgnoreCase("Male"))
        ) {
            throw new IllegalStateException("Same-gender interest is not allowed.");
        }

        // -------------------------------------------------------------
        // 2️⃣ Block duplicates
        // -------------------------------------------------------------
        boolean exists = interestRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId);
        if (exists) {
            throw new IllegalStateException("Interest already sent to this user.");
        }

        // -------------------------------------------------------------
        // 3️⃣ Create interest
        // -------------------------------------------------------------
        ExpressInterest interest = new ExpressInterest();
        interest.setFromUser(fromUser);
        interest.setToUser(toUser);
        interest.setStatus(InterestStatus.SENT);
        interest.setCreatedAt(LocalDateTime.now());
        interest.setMessage(message);

        interestRepository.save(interest);
    }



    // ================================================================
    // ACCEPT INTEREST
    // ================================================================
    @Override
    public void acceptInterest(Integer currentUserId, Long interestId) {

        ExpressInterest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new RuntimeException("Interest request not found"));

        if (!interest.getToUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to accept this request");
        }

        interest.setStatus(InterestStatus.ACCEPTED);
        interest.setRespondedAt(LocalDateTime.now());
        interestRepository.save(interest);
    }


    // ================================================================
    // DECLINE INTEREST
    // ================================================================
    @Override
    public void declineInterest(Integer currentUserId, Long interestId) {

        ExpressInterest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new RuntimeException("Interest request not found"));

        if (!interest.getToUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to decline this request");
        }

        interest.setStatus(InterestStatus.DECLINED);
        interest.setRespondedAt(LocalDateTime.now());
        interestRepository.save(interest);
    }


    // ================================================================
    // GET RECEIVED INTERESTS
    // ================================================================

    @Override
    public Page<InterestResponseDTO> getReceived(Integer userId, Pageable pageable) {

        return interestRepository.findByToUserId(userId, pageable)
                .map(mapper::toDTO);
    }



    // ================================================================
    // GET SENT INTERESTS
    // ================================================================
    @Override
    public Page<InterestResponseDTO> getSent(Integer userId, Pageable pageable) {

        return interestRepository.findByFromUserId(userId, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Page<InterestResponseDTO> getPending(Integer userId, Pageable pageable) {
        return null;
    }


}
