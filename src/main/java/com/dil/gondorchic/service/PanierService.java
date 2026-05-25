package com.dil.gondorchic.service;

import com.dil.gondorchic.dto.AjoutPanierRequest;
import com.dil.gondorchic.dto.AjoutPanierResponse;
import com.dil.gondorchic.entity.Client;
import com.dil.gondorchic.entity.LignePanier;
import com.dil.gondorchic.entity.Panier;
import com.dil.gondorchic.entity.Produit;
import com.dil.gondorchic.repository.ClientRepository;
import com.dil.gondorchic.repository.PanierRepository;
import com.dil.gondorchic.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PanierService {

    private final PanierRepository panierRepository;
    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;

    @Transactional
    public AjoutPanierResponse ajouterProduit(AjoutPanierRequest request) {
        // Validation de la quantité
        if (request.getQuantite() == null || request.getQuantite() <= 0) {
            return AjoutPanierResponse.error("La quantité doit être supérieure à 0");
        }

        // Vérification du client
        Client client = clientRepository.findById(request.getClientId()).orElse(null);
        if (client == null) {
            return AjoutPanierResponse.error("Client introuvable");
        }

        // Vérification du produit
        Produit produit = produitRepository.findById(request.getProduitId()).orElse(null);
        if (produit == null) {
            return AjoutPanierResponse.error("Produit introuvable");
        }

        // Récupération ou création du panier
        Panier panier = panierRepository.findByClientId(request.getClientId())
                .orElseGet(() -> {
                    Panier nouveauPanier = new Panier();
                    nouveauPanier.setClient(client);
                    return panierRepository.save(nouveauPanier);
                });

        // Vérification si le produit existe déjà dans le panier
        LignePanier ligneExistante = panier.getLignes().stream()
                .filter(ligne -> ligne.getProduit().getId().equals(request.getProduitId()))
                .findFirst()
                .orElse(null);

        if (ligneExistante != null) {
            // Mise à jour de la quantité
            ligneExistante.setQuantite(ligneExistante.getQuantite() + request.getQuantite());
        } else {
            // Ajout d'une nouvelle ligne
            LignePanier nouvelleLigne = new LignePanier();
            nouvelleLigne.setPanier(panier);
            nouvelleLigne.setProduit(produit);
            nouvelleLigne.setQuantite(request.getQuantite());
            panier.getLignes().add(nouvelleLigne);
        }

        panierRepository.save(panier);

        return AjoutPanierResponse.success();
    }
}
