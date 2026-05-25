package com.dil.gondorchic.service;

import com.dil.gondorchic.dto.PageAccueilResponse;
import com.dil.gondorchic.dto.ProduitDuJourDto;
import com.dil.gondorchic.entity.Client;
import com.dil.gondorchic.entity.Produit;
import com.dil.gondorchic.repository.ClientRepository;
import com.dil.gondorchic.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageAccueilService {

    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;

    public PageAccueilResponse getPageAccueil(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Produit produitDuJour = produitRepository.findByProduitDuJourTrue()
                .orElse(null);

        ProduitDuJourDto produitDto = null;
        if (produitDuJour != null) {
            produitDto = new ProduitDuJourDto(
                    produitDuJour.getId(),
                    produitDuJour.getLibelle(),
                    produitDuJour.getPrix(),
                    produitDuJour.getQuantiteStock(),
                    produitDuJour.getImageUrl()
            );
        }

        return new PageAccueilResponse(client.getPrenom(), client.getNom(), produitDto);
    }
}
