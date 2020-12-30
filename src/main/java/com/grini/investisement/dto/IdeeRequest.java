package com.grini.investisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeeRequest {
	
	
    private Long ideeId;
	
	private String titre;
	
	private String secteur ;
	
	private int budget;
	
	private String textDescriptif;
	
	private String commune;

}
