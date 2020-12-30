package com.grini.investisement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);
	
}
