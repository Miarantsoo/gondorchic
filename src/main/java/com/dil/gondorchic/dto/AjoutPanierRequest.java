package com.dil.gondorchic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjoutPanierRequest {
    private Long clientId;
    private Long produitId;
    private Integer quantite;
}
