package com.grini.investisement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;
import com.grini.investisement.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	Optional<Vote> findTopByIdeeAndUserOrderByVoteIdDesc(Idee idee, User currentUser);

}
