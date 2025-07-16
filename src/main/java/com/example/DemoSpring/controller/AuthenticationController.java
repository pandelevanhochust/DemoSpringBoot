package com.example.DemoSpring.controller;

import com.example.DemoSpring.dto.LoginRequest;
import com.example.DemoSpring.entity.UserModel;
import com.example.DemoSpring.security.jwt.JwtService;
import com.example.DemoSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authentication.getName());
        }

        return loginRequest.getUsername();
    }

    @PostMapping("/register")
    public void register(@RequestBody LoginRequest registerRequest){
        UserModel userRegister = new UserModel(null,null,registerRequest.getUsername(), registerRequest.getPassword(),null,null,null,null);
        userService.createUser(userRegister);
    }
}
