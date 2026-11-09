package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Vehicule;
import java.math.BigDecimal;

public interface ITarificationService {
    BigDecimal calculerTarifTotal(Vehicule vehicule, int nbJours);
}