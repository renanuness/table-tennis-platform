package com.ttplatform.profile.domain.service;

import com.ttplatform.profile.domain.model.UserProfile;
import com.ttplatform.profile.domain.message.CreateUserProfileMessage;
import com.ttplatform.profile.domain.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

    private final UserProfileRepository repository;

    public ProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }


    public void createUserProfile(CreateUserProfileMessage message){
        var userProfile = new UserProfile(UUID.fromString(message.id()), message.name(), message.email());

        repository.save(userProfile);
    }
}
