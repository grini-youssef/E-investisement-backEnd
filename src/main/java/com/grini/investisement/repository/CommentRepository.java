package com.grini.investisement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.Comment;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByIdee(Idee idee);
	
	List<Comment> findByUser(User user);
	
	
	
}
