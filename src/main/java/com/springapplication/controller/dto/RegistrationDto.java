package com.springapplication.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RegistrationDto {
    private Long userId;
}
