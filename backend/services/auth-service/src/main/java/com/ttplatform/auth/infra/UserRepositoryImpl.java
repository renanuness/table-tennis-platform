package com.ttplatform.auth.infra;

import com.ttplatform.auth.domain.interfaces.repositories.UserRepository;
import com.ttplatform.auth.domain.models.User;
import com.ttplatform.auth.infra.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa jpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public User save(User usuario) {
        var entity = jpaRepository.save(UserEntity.fromDomain(usuario));
        return entity.toDomain();
    }

    @Override
    public List<User> listAll() {

        return jpaRepository.findAll().stream().map(UserEntity::toDomain).toList();
    }
}