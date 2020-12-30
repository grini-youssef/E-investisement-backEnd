package com.grini.investisement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;

public interface IdeeRepository extends JpaRepository<Idee, Long> {
	
	List<Idee> findByUser(User user);
}
