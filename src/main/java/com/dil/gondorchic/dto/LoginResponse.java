package com.dil.gondorchic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private List<String> erreurs;
    private Long clientId;
    private String prenom;
    private String nom;

    public static LoginResponse success(Long clientId, String prenom, String nom) {
        return new LoginResponse(true, "Connexion réussie", null, clientId, prenom, nom);
    }

    public static LoginResponse error(List<String> erreurs) {
        return new LoginResponse(false, "Échec de la connexion", erreurs, null, null, null);
    }
}
