package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Long id, Vehicule vehicule);
    void supprimerVehicule(Long id);
    Vehicule consulterVehicule(Long id);
    List<Vehicule> listerVehicules();
}