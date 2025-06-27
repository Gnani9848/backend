package com.secur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.secur.entity.UserCredential;
import com.secur.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "User added to the system";
    }

    public String generateToken(String username) {
        UserCredential user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return jwtService.generateToken(username, user.getRole());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public String validateTokenAndGetRole(String token) {
        jwtService.validateToken(token); // Validate the token
        String role = jwtService.extractRole(token); // Extract the role
        System.out.println("Token validated. Role: " + role); // Log the role
        return role;
    }

    // Add the loadUserByUsername method
    public UserCredential loadUserByUsername(String username) {
        return repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
