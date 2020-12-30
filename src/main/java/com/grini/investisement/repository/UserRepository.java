package com.grini.investisement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
