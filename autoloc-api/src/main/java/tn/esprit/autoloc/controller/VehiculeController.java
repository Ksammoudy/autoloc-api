package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Vehicule; // adapte le package de ton entité
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeController {

    IVehiculeService vehiculeService;

    // http://localhost:8089/autoloc/vehicule/retrieve-all-vehicules
    @GetMapping("/retrieve-all-vehicules")
    public List<Vehicule> getVehicules() {
        return vehiculeService.listerVehicules();
    }

    // http://localhost:8089/autoloc/vehicule/retrieve-vehicule/8
    @GetMapping("/retrieve-vehicule/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return vehiculeService.consulterVehicule(vehiculeId);
    }

    // http://localhost:8089/autoloc/vehicule/add-vehicule?idAgence=1
    @PostMapping("/add-vehicule")
    public Vehicule addVehicule(@RequestBody Vehicule v, @RequestParam Long idAgence) {
        return vehiculeService.ajouterVehicule(v, idAgence);
    }

    // http://localhost:8089/autoloc/vehicule/remove-vehicule/8
    @DeleteMapping("/remove-vehicule/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        vehiculeService.supprimerVehicule(vehiculeId);
    }

    // http://localhost:8089/autoloc/vehicule/modify-vehicule/8?idAgence=1
    @PutMapping("/modify-vehicule/{vehicule-id}")
    public Vehicule modifyVehicule(@PathVariable("vehicule-id") Long vehiculeId,
                                   @RequestBody Vehicule v,
                                   @RequestParam Long idAgence) {
        return vehiculeService.modifierVehicule(vehiculeId, v, idAgence);
    }
}