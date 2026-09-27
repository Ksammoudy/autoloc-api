package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.strategy.ITarificationStrategy;
import tn.esprit.autoloc.service.strategy.TarificationStrategyFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TarificationServiceImpl implements ITarificationService {

    private final TarificationStrategyFactory strategyFactory;

    public TarificationServiceImpl(TarificationStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @Override
    public BigDecimal calculerTarifTotal(Vehicule vehicule, int nbJours) {
        ITarificationStrategy strategy = strategyFactory.getStrategy(vehicule.getCategorie());
        return strategy.calculerTarif(vehicule.getTarifJournalier(), nbJours);
    }
}