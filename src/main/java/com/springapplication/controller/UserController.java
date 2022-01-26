package com.springapplication.controller;

import com.springapplication.controller.dto.RegistrationDto;
import com.springapplication.controller.request.LoginRequest;
import com.springapplication.controller.request.RegistrationRequest;
import com.springapplication.controller.response.LoginResponse;
import com.springapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }


    @PostMapping("/registration")
    public RegistrationDto registration(@RequestBody RegistrationRequest registrationRequest) {
        return RegistrationDto.builder()
                .userId(userService.registration(registrationRequest))
                .build();
    }
}
