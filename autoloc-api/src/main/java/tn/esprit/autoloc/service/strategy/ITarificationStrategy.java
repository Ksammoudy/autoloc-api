package tn.esprit.autoloc.service.strategy;

import java.math.BigDecimal;

public interface ITarificationStrategy {
    BigDecimal calculerTarif(BigDecimal tarifJournalier, int nbJours);
}