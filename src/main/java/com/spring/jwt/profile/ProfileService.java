package com.spring.jwt.profile;

public interface ProfileService {

    ProfileDTO createProfile(Integer userId, ProfileDTO profileDTO);
}
