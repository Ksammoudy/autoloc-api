package tn.esprit.autoloc.service.strategy;

import tn.esprit.autoloc.domain.CategorieVehicule;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class TarificationStrategyFactory {

    private final Map<CategorieVehicule, ITarificationStrategy> strategies;

    public TarificationStrategyFactory(
            TarifCitadineStrategy citadine,
            TarifBerlineStrategy berline,
            TarifSuvStrategy suv,
            TarifUtilitaireStrategy utilitaire) {

        strategies = new EnumMap<>(CategorieVehicule.class);
        strategies.put(CategorieVehicule.CITADINE, citadine);
        strategies.put(CategorieVehicule.BERLINE, berline);
        strategies.put(CategorieVehicule.SUV, suv);
        strategies.put(CategorieVehicule.UTILITAIRE, utilitaire);
    }

    public ITarificationStrategy getStrategy(CategorieVehicule categorie) {
        ITarificationStrategy strategy = strategies.get(categorie);
        if (strategy == null) {
            throw new IllegalArgumentException("Aucune stratégie de tarification pour la catégorie : " + categorie);
        }
        return strategy;
    }
}