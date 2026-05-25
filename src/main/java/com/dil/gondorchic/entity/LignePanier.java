package com.dil.gondorchic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lignes_panier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "panier_id", nullable = false)
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private Integer quantite;
}
