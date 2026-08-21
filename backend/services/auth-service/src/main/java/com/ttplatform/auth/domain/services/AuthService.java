package com.ttplatform.auth.domain.services;

import com.ttplatform.auth.domain.exceptions.LoginInvalidoException;
import com.ttplatform.auth.domain.models.User;
import com.ttplatform.auth.presentation.dtos.AuthResponse;
import com.ttplatform.auth.presentation.dtos.LoginRequest;
import com.ttplatform.auth.presentation.dtos.RegisterRequest;
import com.ttplatform.auth.domain.interfaces.repositories.UserRepository;
import com.ttplatform.auth.domain.exceptions.UserAlreadyExistsException;
import com.ttplatform.auth.security.PasswordManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email já cadastrado: " + request.getEmail());
        }

        var hashedPassword = PasswordManager.hashPassword(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                hashedPassword
        );


        User savedUser = userRepository.save(user);

        return new AuthResponse(savedUser);
    }

    public AuthResponse login(LoginRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new LoginInvalidoException());

        if(user.isPasswordCorrect(request.getPassword())) {
            return new AuthResponse(user);
        }

        throw new LoginInvalidoException();
    }
}