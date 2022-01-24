package com.springapplication.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
}
