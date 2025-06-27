package com.secur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.secur.entity.UserCredential;
import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByUsername(String username);
}