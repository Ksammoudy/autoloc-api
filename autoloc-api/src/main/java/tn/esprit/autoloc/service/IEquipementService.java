package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    List<Equipement> listerEquipements();
    void affecterEquipementAVehicule(Long idEquipement, Long idVehicule);
    void desaffecterEquipementDeVehicule(Long idEquipement, Long idVehicule);
}