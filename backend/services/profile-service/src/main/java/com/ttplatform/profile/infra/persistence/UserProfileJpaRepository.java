package com.ttplatform.profile.infra.persistence;

import com.ttplatform.profile.infra.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfileEntity, UUID> {
}
