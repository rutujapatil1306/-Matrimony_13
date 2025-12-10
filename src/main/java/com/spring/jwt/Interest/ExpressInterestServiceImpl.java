package com.spring.jwt.Interest;

import com.spring.jwt.entity.ExpressInterest;
import com.spring.jwt.Enums.Gender;
import com.spring.jwt.Enums.InterestStatus;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.InterestAlreadySentException;
import com.spring.jwt.exception.InterestNotFoundException;
import com.spring.jwt.exception.UserNotFoundExceptions;
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

    @Override
    public void sendInterest(Integer fromUserId, Integer toUserId, String message) {

        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("You cannot send interest to yourself.");
        }

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new UserNotFoundExceptions("Sender user not found"));

        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new UserNotFoundExceptions("Receiver user not found"));

        Gender senderGender = fromUser.getGender();
        Gender receiverGender = toUser.getGender();

        if (senderGender == receiverGender) {
            throw new IllegalStateException("You can send interest only to opposite gender.");
        }

        boolean exists = interestRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId);
        if (exists) {
            throw new InterestAlreadySentException("Interest already sent to this user.");
        }

        ExpressInterest interest = new ExpressInterest();
        interest.setFromUser(fromUser);
        interest.setToUser(toUser);
        interest.setStatus(InterestStatus.PENDING);
        interest.setCreatedAt(LocalDateTime.now());
        interest.setMessage(message);

        interestRepository.save(interest);
    }



    @Override
    public void acceptInterest(Integer currentUserId, Long interestId) {

        ExpressInterest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));

        if (!interest.getToUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to accept this request");
        }

        interest.setStatus(InterestStatus.ACCEPTED);
        interest.setRespondedAt(LocalDateTime.now());
        interestRepository.save(interest);
    }

    @Override
    public void declineInterest(Integer currentUserId, Long interestId) {

        ExpressInterest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));

        if (!interest.getToUser().getId().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to decline this request");
        }

        interest.setStatus(InterestStatus.DECLINED);
        interest.setRespondedAt(LocalDateTime.now());
        interestRepository.save(interest);
    }

    @Override
    public Page<InterestResponseDTO> getReceivedInterests(Integer userId, Pageable pageable) {
        Page<ExpressInterest> interests =
                interestRepository.findByToUserIdAndStatus(userId, InterestStatus.PENDING, pageable);
        return interests.map(mapper::toDTO);
    }


    @Override
    public Page<InterestResponseDTO> getSentInterests(Integer userId, Pageable pageable) {

        return interestRepository.findByFromUserId(userId, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Page<InterestResponseDTO> getPendingReceived(Integer userId, Pageable pageable) {

        Page<ExpressInterest> page = interestRepository.findByToUserIdAndStatus(userId, InterestStatus.PENDING, pageable);
        return page.map(mapper::toDTO);
    }

    @Override
    public Page<InterestResponseDTO> getPendingSent(Integer userId, Pageable pageable) {

        Page<ExpressInterest> page = interestRepository.findByFromUserIdAndStatus(userId, InterestStatus.PENDING, pageable);
        return page.map(mapper::toDTO);
    }



}
