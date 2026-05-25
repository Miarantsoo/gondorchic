package com.dil.gondorchic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjoutPanierResponse {
    private boolean success;
    private String message;

    public static AjoutPanierResponse success() {
        return new AjoutPanierResponse(true, "Produit ajouté au panier avec succès");
    }

    public static AjoutPanierResponse error(String message) {
        return new AjoutPanierResponse(false, message);
    }
}
