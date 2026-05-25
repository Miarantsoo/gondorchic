package com.dil.gondorchic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAccueilResponse {
    private String prenom;
    private String nom;
    private ProduitDuJourDto produitDuJour;
}
