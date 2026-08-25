package com.ttplatform.auth.infra.data;


import com.ttplatform.auth.infra.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
