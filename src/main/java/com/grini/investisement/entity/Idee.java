package com.grini.investisement.entity;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "idee")
public class Idee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ideeId;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "secteur")
	private String secteur ;
	
	@Column(name = "budget")
	private int budget;
	
	@Column(name = "realise")
	private boolean realise = false ;
	
	private int voteCount = 0;
	
	private Instant createdDate;
	
	private String textDescriptif;
	
	// url for image or video
	//private String urlFile;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "communeId", referencedColumnName = "communeId")
	private Commune commune;
	
}
