package com.dil.gondorchic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDuJourDto {
    private Long id;
    private String libelle;
    private BigDecimal prix;
    private Integer quantiteStock;
    private String imageUrl;
}
