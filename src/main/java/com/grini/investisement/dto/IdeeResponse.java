package com.grini.investisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeeResponse {
	
	private Long ideeId;
	
	private String titre;
	
	private String secteur ;
	
	private int budget;
	
	private String textDescriptif;
	
	private String commune;
	
    private String userName;

    private Integer voteCount;
    
    private Integer commentCount;
    
    private String duration;
    
    private String phone;
    
    private boolean upVote;
    
    private boolean downVote;

}
