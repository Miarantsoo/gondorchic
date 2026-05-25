package com.dil.gondorchic.controller;

import com.dil.gondorchic.dto.AjoutPanierRequest;
import com.dil.gondorchic.dto.AjoutPanierResponse;
import com.dil.gondorchic.dto.PageAccueilResponse;
import com.dil.gondorchic.service.PageAccueilService;
import com.dil.gondorchic.service.PanierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accueil")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PageAccueilController {

    private final PageAccueilService pageAccueilService;
    private final PanierService panierService;

    @GetMapping("/{clientId}")
    public ResponseEntity<PageAccueilResponse> getPageAccueil(@PathVariable Long clientId) {
        PageAccueilResponse response = pageAccueilService.getPageAccueil(clientId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/panier/ajouter")
    public ResponseEntity<AjoutPanierResponse> ajouterAuPanier(@RequestBody AjoutPanierRequest request) {
        AjoutPanierResponse response = panierService.ajouterProduit(request);
        return ResponseEntity.ok(response);
    }
}
