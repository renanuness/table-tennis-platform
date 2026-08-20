package com.ttplatform.auth.domain.interfaces.repositories;

import com.ttplatform.auth.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User save(User usuario);
    List<User> listAll();
}
