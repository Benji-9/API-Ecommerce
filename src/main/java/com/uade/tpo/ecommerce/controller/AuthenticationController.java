package com.uade.tpo.ecommerce.controller;

import com.uade.tpo.ecommerce.dto.LoginRequest;
import com.uade.tpo.ecommerce.dto.RegisterRequest;
import com.uade.tpo.ecommerce.dto.RegisterResponse;
import com.uade.tpo.ecommerce.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * REGISTRO DE USUARIO
     * URL: POST http://localhost:8081/api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * LOGIN DE USUARIO
     * URL: POST http://localhost:8081/api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}