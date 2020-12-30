package com.grini.investisement.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;
	
	private VoteType voteType;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ideeId", referencedColumnName = "ideeId")
    private Idee idee;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
	
}







