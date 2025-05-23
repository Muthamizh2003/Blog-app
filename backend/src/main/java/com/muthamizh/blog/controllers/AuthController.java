package com.muthamizh.blog.controllers;

import com.muthamizh.blog.domain.dtos.AuthResponse;
import com.muthamizh.blog.domain.dtos.LoginRequest;
import com.muthamizh.blog.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/login")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping()
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails user = authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        AuthResponse authResponse = AuthResponse.builder()
                .token(authenticationService.generateToken(user))
                .expiresIn(86400) // 24 hours in seconds
                .build();
        return ResponseEntity.ok(authResponse);
    }
}