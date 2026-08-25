package com.ttplatform.auth.domain.service;

import com.ttplatform.auth.domain.events.Publisher;
import com.ttplatform.auth.domain.events.UserCreatedEvent;
import com.ttplatform.auth.domain.exception.LoginInvalidoException;
import com.ttplatform.auth.domain.exception.UserNotFoundExcpetion;
import com.ttplatform.auth.domain.model.User;
import com.ttplatform.auth.application.dto.AuthResponse;
import com.ttplatform.auth.application.dto.LoginRequest;
import com.ttplatform.auth.application.dto.RegisterRequest;
import com.ttplatform.auth.domain.repository.UserRepository;
import com.ttplatform.auth.domain.exception.UserAlreadyExistsException;
import com.ttplatform.auth.security.PasswordManager;
import com.ttplatform.auth.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Publisher publisher;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email já cadastrado: " + request.getEmail());
        }

        var hashedPassword = PasswordManager.hashPassword(request.getPassword());
        User user = User.create(
                request.getName(),
                request.getEmail(),
                hashedPassword
        );


        User savedUser = userRepository.save(user);
        publisher.Send(new UserCreatedEvent(savedUser.getId(), savedUser.getName(), savedUser.getEmail()));
        String token = TokenManager.createToken(savedUser);
        return new AuthResponse(savedUser, token);
    }

    public AuthResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new LoginInvalidoException());

        if(PasswordManager.verifyPassword(request.getPassword(), user.getPassword())) {
            String token = TokenManager.createToken(user);
            return new AuthResponse(user, token);
        }

        throw new LoginInvalidoException();
    }

    public User getUserById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundExcpetion());

        return user;
    }
}