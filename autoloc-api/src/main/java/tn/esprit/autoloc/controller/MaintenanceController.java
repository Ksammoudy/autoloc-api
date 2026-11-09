package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Maintenance; // adapte le package de ton entité
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {

    IMaintenanceService maintenanceService;

    // http://localhost:8089/autoloc/maintenance/retrieve-all-maintenances
    @GetMapping("/retrieve-all-maintenances")
    public List<Maintenance> getMaintenances() {
        return maintenanceService.listerMaintenances();
    }

    // http://localhost:8089/autoloc/maintenance/retrieve-maintenance/8
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public Maintenance retrieveMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.consulterMaintenance(maintenanceId);
    }

    // http://localhost:8089/autoloc/maintenance/retrieve-maintenances-vehicule/5
    @GetMapping("/retrieve-maintenances-vehicule/{vehicule-id}")
    public List<Maintenance> getMaintenancesDuVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return maintenanceService.listerMaintenancesDuVehicule(vehiculeId);
    }

    // http://localhost:8089/autoloc/maintenance/add-maintenance?idVehicule=5
    @PostMapping("/add-maintenance")
    public Maintenance addMaintenance(@RequestBody Maintenance m, @RequestParam Long idVehicule) {
        return maintenanceService.planifierMaintenance(m, idVehicule);
    }

    // http://localhost:8089/autoloc/maintenance/finish-maintenance/8
    @PutMapping("/finish-maintenance/{maintenance-id}")
    public Maintenance finishMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.terminerMaintenance(maintenanceId);
    }
}