package com.spring.jwt.profile;

public interface ProfileService {

    ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO);

    ProfileDTO updateProfile(Integer userId, ProfileDTO dto);

    ProfileDTO getProfile(Integer userId);

    void deleteProfile(Integer userId);
}
