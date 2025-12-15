package com.spring.jwt.Interest;

import com.spring.jwt.entity.ExpressInterest;
import com.spring.jwt.Enums.Gender;
import com.spring.jwt.Enums.InterestStatus;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public void sendInterest(Integer fromUserId, Integer toUserId) {

        boolean exists = interestRepository.existsByFromUserIdAndToUserId(fromUserId, toUserId);
        if (exists) {
            throw new InterestAlreadySentException("Interest already sent to this user.");
        }

        if (fromUserId.equals(toUserId)) {
            throw new InvalidOperationException("You cannot send interest to yourself.");
        }

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new UserNotFoundExceptions("Sender user not found"));

        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new UserNotFoundExceptions("Receiver user not found"));

        Gender senderGender = fromUser.getGender();
        Gender receiverGender = toUser.getGender();

        if (senderGender == receiverGender) {
            throw new InvalidOperationException("You can send interest only to opposite gender.");
        }

        ExpressInterest interest = new ExpressInterest();
        interest.setFromUser(fromUser);
        interest.setToUser(toUser);
        interest.setStatus(InterestStatus.PENDING);
        interest.setCreatedAt(LocalDateTime.now());
        //interest.setMessage(message);

        interestRepository.save(interest);
    }



    @Override
    public InterestResponseDTO acceptInterest(Integer currentUserId, Long interestId) {

        if (currentUserId == null) {
            throw new MissingParameterException("Current user ID cannot be null");
        }
        if (interestId == null) {
            throw new MissingParameterException("Interest ID cannot be null");
        }

        ExpressInterest interest = interestRepository
                .findByInterestIdAndToUserId(interestId, currentUserId)
                .orElseThrow(() ->
                        new InvalidInterestException("Invalid interest ID or unauthorized access")
                );

        if (interest.getStatus() != InterestStatus.PENDING) {
            throw new InvalidInterestException(
                    "Interest already"+" "+interest.getStatus());
        }

        interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));


        interest.setStatus(InterestStatus.ACCEPTED);
        interest.setRespondedAt(LocalDateTime.now());
        ExpressInterest updated = interestRepository.save(interest);
        return mapper.toDTO(updated);

    }

    @Override
    public InterestResponseDTO declineInterest(Integer currentUserId, Long interestId) {

        if (currentUserId == null) {
            throw new MissingParameterException("Current user ID cannot be null");
        }
        if (interestId == null) {
            throw new MissingParameterException("Interest ID cannot be null");
        }

        ExpressInterest interest = interestRepository
                .findByInterestIdAndToUserId(interestId, currentUserId)
                .orElseThrow(() ->
                        new InvalidInterestException("Invalid interest ID or unauthorized access")
                );

        if (interest.getStatus() != InterestStatus.PENDING) {
            throw new InvalidInterestException(
                    "Interest already"+" "+interest.getStatus());
        }

                 interestRepository.findById(interestId)
                .orElseThrow(() -> new InterestNotFoundException("Interest request not found"));


        interest.setStatus(InterestStatus.DECLINED);
        interest.setRespondedAt(LocalDateTime.now());
        ExpressInterest updated = interestRepository.save(interest);

        return mapper.toDTO(updated);

    }

    @Override
    public Page<InterestResponseDTO> getReceivedInterests(Integer userId, InterestStatus status, Pageable pageable) {
        Page<ExpressInterest> interests =
                interestRepository.findByToUserIdAndStatus(userId, status, pageable);
        return interests.map(mapper::toDTO);
    }


    @Override
    public Page<InterestResponseDTO> getSentInterests(Integer userId, InterestStatus status, Pageable pageable) {
        Page<ExpressInterest> result =
                interestRepository.findByFromUserIdAndStatus(userId, status, pageable);
        return result.map(mapper::toDTO);
    }

}
