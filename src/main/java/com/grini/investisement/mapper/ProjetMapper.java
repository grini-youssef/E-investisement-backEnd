package com.grini.investisement.mapper;


import com.grini.investisement.dto.ProjetRequest;
import com.grini.investisement.dto.ProjetResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Projet;
import com.grini.investisement.entity.User;
import com.grini.investisement.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class ProjetMapper {
    @Autowired
    private AuthService authService;

    public Projet map(ProjetRequest projetRequest, Commune commune , User user) {
        Projet projet = new Projet();

        projet.setTitre(projetRequest.getTitre());
        projet.setSecteur(projetRequest.getSecteur());
        projet.setTurnover(projetRequest.getTurnover());
        projet.setImageUrl(projet.getImageUrl());
        projet.setCommune(commune);
        projet.setUser(user);
        projet.setDescription(projetRequest.getDescription());
        projet.setDate_created(java.time.Instant.now());

        return projet;
    }

    public ProjetResponse mapToDto(Projet projet) {
        ProjetResponse projetResponse = new ProjetResponse();

        projetResponse.setIdProjet(projet.getIdProjet());
        projetResponse.setTitre(projet.getTitre());
        projetResponse.setSecteur(projet.getSecteur());
        projetResponse.setDescription(projet.getDescription());
        projetResponse.setImageUrl(projet.getImageUrl());
        projetResponse.setTurnover(projet.getTurnover());
        projetResponse.setCommune(projet.getCommune().getNom());
        projetResponse.setUserName(projet.getUser().getUsername());


        return projetResponse;
    }

}
