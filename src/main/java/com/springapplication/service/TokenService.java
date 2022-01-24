package com.springapplication.service;

import com.springapplication.controller.response.RefreshTokenResponse;
import com.springapplication.model.User;
import com.springapplication.repository.UserRepository;
import com.springapplication.security.jwt.JwtTokenProvider;
import com.springapplication.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public RefreshTokenResponse refresh(String token, Long id) {

        User user = userRepository.findUserById(id).orElseThrow(() ->
                new UserNotFoundException("User not found"));

        jwtTokenProvider.validateToken(token);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
