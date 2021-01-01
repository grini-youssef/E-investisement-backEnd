package com.grini.investisement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grini.investisement.dto.VoteDto;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.Vote;
import com.grini.investisement.exception.IdeeNotFoundException;
import com.grini.investisement.exception.SpringRedditException;
import com.grini.investisement.repository.IdeeRepository;
import com.grini.investisement.repository.VoteRepository;

import static com.grini.investisement.entity.VoteType.UPVOTE;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteServices {

	private final VoteRepository voteRepository;
    private final IdeeRepository ideeRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Idee idee = ideeRepository.findById(voteDto.getIdeeId())
                .orElseThrow(() -> new IdeeNotFoundException("Idee Not Found with ID - " + voteDto.getIdeeId()));
        Optional<Vote> voteByIdeeAndUser = voteRepository.findTopByIdeeAndUserOrderByVoteIdDesc(idee, authService.getCurrentUser());
        System.out.println(voteByIdeeAndUser.toString());
        if (voteByIdeeAndUser.isPresent() &&
        		voteByIdeeAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
        	idee.setVoteCount(idee.getVoteCount() + 1);
        } else {
        	idee.setVoteCount(idee.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, idee));
        ideeRepository.save(idee);
    }

    private Vote mapToVote(VoteDto voteDto, Idee idee) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .idee(idee)
                .user(authService.getCurrentUser())
                .build();
    }
	
}
