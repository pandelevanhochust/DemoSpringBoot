package com.example.DemoSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequest {
    private String username;
    private String password;
    private boolean rememberMe;

}
