package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IAffectationService {

    Vehicule affecterVehiculeAAgence(Long idVehicule, Long idAgence);
    void desaffecterVehiculeDeAgence(Long idVehicule);

    void ajouterEquipementAuVehicule(Long idVehicule, Long idEquipement);
    void retirerEquipementDuVehicule(Long idVehicule, Long idEquipement);

    Employe affecterEmployeAAgence(Long idAgence, Long idEmploye);
    void desaffecterEmployeDeAgence(Long idAgence, Long idEmploye);

    List<Equipement> listerEquipementsDuVehicule(Long idVehicule);
    List<Employe> listerEmployesDeAgence(Long idAgence);
    List<Vehicule> listerVehiculesDeAgence(Long idAgence);
}