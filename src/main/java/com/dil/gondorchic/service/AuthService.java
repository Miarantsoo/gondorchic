package com.dil.gondorchic.service;

import com.dil.gondorchic.dto.LoginRequest;
import com.dil.gondorchic.dto.LoginResponse;
import com.dil.gondorchic.entity.Client;
import com.dil.gondorchic.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClientRepository clientRepository;

    public LoginResponse login(LoginRequest request) {
        List<String> erreurs = new ArrayList<>();

        if (request.getPseudo() == null || request.getPseudo().trim().isEmpty()) {
            erreurs.add("Le pseudo est obligatoire");
        }
        if (request.getMotDePasse() == null || request.getMotDePasse().trim().isEmpty()) {
            erreurs.add("Le mot de passe est obligatoire");
        }
        if (!erreurs.isEmpty()) {
            return LoginResponse.error(erreurs);
        }

        Client client = clientRepository.findByPseudo(request.getPseudo()).orElse(null);

        if (client == null) {
            erreurs.add("Pseudo incorrect");
            return LoginResponse.error(erreurs);
        }

        if (!client.getMotDePasse().equals(request.getMotDePasse())) {
            erreurs.add("Mot de passe incorrect");
            return LoginResponse.error(erreurs);
        }
        return LoginResponse.success(client.getId(), client.getPrenom(), client.getNom());
    }
}
