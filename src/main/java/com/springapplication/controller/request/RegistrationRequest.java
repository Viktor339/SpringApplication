package com.springapplication.controller.request;

import com.springapplication.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private List<Role> roles;
}
