package com.ttplatform.profile.infra.persistence;

import com.ttplatform.profile.domain.model.UserProfile;
import com.ttplatform.profile.domain.repository.UserProfileRepository;
import com.ttplatform.profile.infra.persistence.entity.UserProfileEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {
    private final UserProfileJpaRepository repository;

    public UserProfileRepositoryImpl(UserProfileJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UserProfile userProfile) {
        var entity = UserProfileEntity.fromDomain(userProfile);

        repository.save(entity);
    }
}
