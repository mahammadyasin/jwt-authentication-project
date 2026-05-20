package com.example.jwtlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtlearning.dto.LOginResponseDto;
import com.example.jwtlearning.dto.LoginRequestDto;
import com.example.jwtlearning.dto.SignUpDto;
import com.example.jwtlearning.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("signup")

    public SignUpDto signup(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            return authService.signup(loginRequestDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("login")
    public LOginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @GetMapping("test")
    public String test() {
        return "Hello secured API";
    }

}
