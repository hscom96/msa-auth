package com.auth.userserver.repository;

import com.auth.userserver.model.EmailConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailConfirmTokenRepository extends JpaRepository<EmailConfirmToken, Integer> {
    Optional<EmailConfirmToken> findById(UUID id);
}
