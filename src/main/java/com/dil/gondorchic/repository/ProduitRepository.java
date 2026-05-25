package com.dil.gondorchic.repository;

import com.dil.gondorchic.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByProduitDuJourTrue();
}
