package com.springapplication.controller;

import com.springapplication.controller.request.RefreshTokenRequest;
import com.springapplication.controller.response.RefreshTokenResponse;
import com.springapplication.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return tokenService.refresh(refreshTokenRequest.getRefreshToken(), refreshTokenRequest.getUserId());
    }
}
