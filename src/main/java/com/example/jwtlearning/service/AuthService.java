package com.example.jwtlearning.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwtlearning.dto.LOginResponseDto;
import com.example.jwtlearning.dto.LoginRequestDto;
import com.example.jwtlearning.dto.SignUpDto;
import com.example.jwtlearning.entity.User;
import com.example.jwtlearning.repository.UserRepository;
import com.example.jwtlearning.security.JwtUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtility jwtUtility;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public SignUpDto signup(LoginRequestDto loginRequestDto) {
        try {

            userRepository.findByUsername(loginRequestDto.getUsername())
                    .ifPresent(u -> {
                        throw new IllegalArgumentException("User already exists");
                    });

            User user = User.builder()
                    .username(loginRequestDto.getUsername())
                    .password(encoder.encode(loginRequestDto.getPassword()))
                    .roles("ROLE_USER")
                    .build();

            User savedUser = userRepository.save(user);

            return new SignUpDto(savedUser.getId(), savedUser.getUsername());
        } catch (Exception ex) {
            return null;
        }
    }

    public LOginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()));
            User user = (User) auth.getPrincipal();
            String token = jwtUtility.genrateJwtToken(user);

            return new LOginResponseDto(token, user.getId());
        } catch (Exception ex) {
            return null;
        }

    }

}
