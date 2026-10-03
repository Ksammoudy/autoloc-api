package tn.esprit.autoloc.web.dto;

import java.math.BigDecimal;

public record SoldeContratDTO(
        Long idContrat,
        BigDecimal montantTotal,
        BigDecimal montantPaye,
        BigDecimal resteAPayer
) {
}