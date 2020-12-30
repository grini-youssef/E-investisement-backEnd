package com.grini.investisement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Description {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long descriptionId;
	
	private String texte;
	
	private String urlImage;
	
	private String urlVideo;
	
}
