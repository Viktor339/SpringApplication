package com.springapplication.controller.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private Long userId;
    private String refreshToken;
}
