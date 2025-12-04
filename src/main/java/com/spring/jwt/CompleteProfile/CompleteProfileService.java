package com.spring.jwt.CompleteProfile;

import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.exception.UserNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompleteProfileService {

    private final CompleteProfileRepository completeProfileRepository;
    private final FullProfileMapper mapper;

    public FullProfileDTO getFullProfile(Integer userId) {

        CompleteProfile cp = completeProfileRepository
                .findFullProfileByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("Profile not found for userId : " + userId));

        return mapper.toDTO(cp);
    }

}
