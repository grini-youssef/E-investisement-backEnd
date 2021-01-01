package com.grini.investisement.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.grini.investisement.dto.IdeeRequest;
import com.grini.investisement.dto.IdeeResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;
import com.grini.investisement.repository.CommentRepository;
import com.grini.investisement.repository.VoteRepository;
import com.grini.investisement.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class IdeeMapper {
	
	@Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;
	
	public Idee map(IdeeRequest ideeRequest,Commune commune ,User user) {
		Idee idee = new Idee();
		
		idee.setTitre(ideeRequest.getTitre());
		idee.setSecteur(ideeRequest.getSecteur());
		idee.setBudget(ideeRequest.getBudget());
		idee.setCommune(commune);
		idee.setUser(user);
		idee.setTextDescriptif(ideeRequest.getTextDescriptif());
		idee.setCreatedDate(java.time.Instant.now());
		
		return idee;
	}
	
	public IdeeResponse mapToDto(Idee idee) {
		IdeeResponse ideeResponse = new IdeeResponse();
		
		ideeResponse.setIdeeId(idee.getIdeeId());
		ideeResponse.setTitre(idee.getTitre());
		ideeResponse.setSecteur(idee.getSecteur());
		ideeResponse.setBudget(idee.getBudget());
		ideeResponse.setTextDescriptif(idee.getTextDescriptif());
		ideeResponse.setCommune(idee.getCommune().getNom());
		ideeResponse.setUserName(idee.getUser().getUsername());
		ideeResponse.setCommentCount(commentCount(idee));
		ideeResponse.setDuration(getDuration(idee));
		ideeResponse.setVoteCount(idee.getVoteCount());
		
		
		return ideeResponse;
	}
	
	Integer commentCount(Idee idee) {
        return commentRepository.findByIdee(idee).size();
    }

    String getDuration(Idee idee) {
        return TimeAgo.using(idee.getCreatedDate().toEpochMilli());
    }

}
