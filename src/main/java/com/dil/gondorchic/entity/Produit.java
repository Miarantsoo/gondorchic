package com.dil.gondorchic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private BigDecimal prix;

    @Column(nullable = false)
    private Integer quantiteStock;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean produitDuJour = false;
}
