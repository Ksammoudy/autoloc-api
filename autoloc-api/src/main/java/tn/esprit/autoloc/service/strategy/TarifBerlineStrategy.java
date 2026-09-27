package tn.esprit.autoloc.service.strategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TarifBerlineStrategy implements ITarificationStrategy {
    @Override
    public BigDecimal calculerTarif(BigDecimal tarifJournalier, int nbJours) {
        return tarifJournalier.multiply(BigDecimal.valueOf(nbJours)).multiply(BigDecimal.valueOf(1.1));
    }
}