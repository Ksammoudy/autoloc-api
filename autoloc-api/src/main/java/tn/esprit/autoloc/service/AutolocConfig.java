package tn.esprit.autoloc.service;

import org.springframework.stereotype.Component;

@Component
public class AutolocConfig {

    private final String nomEntreprise = "AutoLoc";
    private final double tauxTVA = 0.19;

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }
}