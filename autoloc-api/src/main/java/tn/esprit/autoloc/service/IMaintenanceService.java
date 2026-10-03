package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance planifierMaintenance(Maintenance maintenance, Long idVehicule);
    Maintenance terminerMaintenance(Long id);
    Maintenance consulterMaintenance(Long id);
    List<Maintenance> listerMaintenances();
    List<Maintenance> listerMaintenancesDuVehicule(Long idVehicule);
}