package com.grini.investisement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}