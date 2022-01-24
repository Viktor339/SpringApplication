package com.springapplication.controller.dto;

import com.springapplication.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class UserDto {
    private String username;
    private String password;
    private List<Role> roles;
}
