package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule, Long idAgence);
    Vehicule modifierVehicule(Long id, Vehicule vehicule, Long idAgence);
    void supprimerVehicule(Long id);
    Vehicule consulterVehicule(Long id);
    List<Vehicule> listerVehicules();
}