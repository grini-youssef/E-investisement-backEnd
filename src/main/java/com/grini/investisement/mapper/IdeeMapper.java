package com.grini.investisement.mapper;

import org.springframework.stereotype.Component;

import com.grini.investisement.dto.IdeeRequest;
import com.grini.investisement.dto.IdeeResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class IdeeMapper {
	
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
		
		
		return ideeResponse;
	}

}
