package com.dil.gondorchic;

import com.dil.gondorchic.entity.Client;
import com.dil.gondorchic.entity.Produit;
import com.dil.gondorchic.repository.ClientRepository;
import com.dil.gondorchic.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final String CHAUDRON_MAGIQUE = "Chaudron magique";
    private static final String CAPE_MAGIQUE = "Cape magique";
    private static final String COFFRET_BIJOUX = "Coffret de bijoux de fondcombe";

    private static final Map<String, String> PRODUCT_IMAGE_URLS = Map.of(
            CHAUDRON_MAGIQUE, "/assets/chaudron.jpeg",
            CAPE_MAGIQUE, "/assets/cape.jpg",
            COFFRET_BIJOUX, "/assets/coffre.jpg"
    );

    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;

    @Override
    public void run(String... args) {
        if (clientRepository.count() == 0) {
            clientRepository.saveAll(List.of(
                    new Client(null, "Leporteur", "ItotoXS", "Frodon", "Sacquet"),
                    new Client(null, "Lebrave", "titiXXL", "Sam", "Gamegie"),
                    new Client(null, "Lefestif", "InainXXL", "Pippin", "Touque")
            ));
        }

        if (produitRepository.count() == 0) {
            produitRepository.saveAll(List.of(
                    new Produit(
                            null,
                            CHAUDRON_MAGIQUE,
                            BigDecimal.valueOf(250),
                            678,
                            PRODUCT_IMAGE_URLS.get(CHAUDRON_MAGIQUE),
                            false
                    ),
                    new Produit(
                            null,
                            CAPE_MAGIQUE,
                            BigDecimal.valueOf(180),
                            120,
                            PRODUCT_IMAGE_URLS.get(CAPE_MAGIQUE),
                            false
                    ),
                    new Produit(
                            null,
                            COFFRET_BIJOUX,
                            BigDecimal.valueOf(60),
                            15,
                            PRODUCT_IMAGE_URLS.get(COFFRET_BIJOUX),
                            true
                    )
            ));
        }

        backfillProductImages();
    }

    private void backfillProductImages() {
        PRODUCT_IMAGE_URLS.forEach((libelle, imageUrl) ->
                produitRepository.findByLibelle(libelle)
                        .filter(produit -> isBlank(produit.getImageUrl()))
                        .ifPresent(produit -> {
                            produit.setImageUrl(imageUrl);
                            produitRepository.save(produit);
                        })
        );
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
