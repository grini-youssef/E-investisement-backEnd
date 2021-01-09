package com.grini.investisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetResponse {

    private Long idProjet;

    private String titre;

    private String secteur ;

    private String description;

    private String imageUrl;

    private Long turnover;


    private String userName;

    private String commune;
}
