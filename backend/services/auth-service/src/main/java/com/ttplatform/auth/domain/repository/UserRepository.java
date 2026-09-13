package com.ttplatform.auth.domain.repository;

import com.ttplatform.auth.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    User save(User usuario);
    List<User> listAll();
}
