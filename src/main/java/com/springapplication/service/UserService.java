package com.springapplication.service;

import com.springapplication.controller.request.LoginRequest;
import com.springapplication.controller.request.RegistrationRequest;
import com.springapplication.controller.response.LoginResponse;
import com.springapplication.model.User;
import com.springapplication.repository.UserRepository;
import com.springapplication.security.jwt.JwtTokenProvider;
import com.springapplication.service.exception.UserAlreadyExistException;
import com.springapplication.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsernameAndPassword(loginRequest.getLogin(), loginRequest.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Incorrect username or password")
        );

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return LoginResponse.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public Long registration(RegistrationRequest registrationRequest) {

        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }

        User user = userRepository.save(User.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(registrationRequest.getRoles())
                .build());

        return user.getId();
    }
}
