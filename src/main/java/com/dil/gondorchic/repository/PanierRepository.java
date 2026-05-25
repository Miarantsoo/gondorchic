package com.dil.gondorchic.repository;

import com.dil.gondorchic.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
    Optional<Panier> findByClientId(Long clientId);
}
