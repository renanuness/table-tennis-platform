package com.ttplatform.auth.domain.repository;

import com.ttplatform.auth.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User usuario);
    List<User> listAll();
}
