package com.secur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.secur.dto.AuthRequest;
import com.secur.dto.TokenResponse;
import com.secur.entity.UserCredential;
import com.secur.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authenticate.isAuthenticated()) {
            String token = service.generateToken(authRequest.getUsername());
            UserCredential user = service.loadUserByUsername(authRequest.getUsername());
            return new TokenResponse(token, user.getUsername(), user.getRole());
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
        }
        
        @GetMapping("/role")
        public String getRole(@RequestParam("token") String token) {
            return service.validateTokenAndGetRole(token);
        }

        @GetMapping("/forrole")
        public ResponseEntity<Map<String, String>> getRoleFor(@RequestHeader("Authorization") String authorizationHeader) {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
                String role = service.validateTokenAndGetRole(token);
                Map<String, String> response = new HashMap<>();
                response.put("role", role);
                return ResponseEntity.ok(response);
            } else {
                throw new IllegalArgumentException("Authorization header is missing or invalid");
            }
        }
    }
