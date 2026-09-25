package com.ttplatform.profile.domain.repository;

import com.ttplatform.profile.domain.model.UserProfile;

public interface UserProfileRepository {
    void save(UserProfile userProfile);
}
