package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Equipement; // adapte le package de ton entité
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementController {

    IEquipementService equipementService;

    // http://localhost:8089/autoloc/equipement/retrieve-all-equipements
    @GetMapping("/retrieve-all-equipements")
    public List<Equipement> getEquipements() {
        return equipementService.listerEquipements();
    }

    // http://localhost:8089/autoloc/equipement/add-equipement
    @PostMapping("/add-equipement")
    public Equipement addEquipement(@RequestBody Equipement e) {
        return equipementService.ajouterEquipement(e);
    }
    @PutMapping("/affecter-equipement/{equipement-id}/vehicule/{vehicule-id}")
    public void affecterEquipement(@PathVariable("equipement-id") Long e, @PathVariable("vehicule-id") Long v) {
        equipementService.affecterEquipementAVehicule(e, v);
    }

    @PutMapping("/desaffecter-equipement/{equipement-id}/vehicule/{vehicule-id}")
    public void desaffecterEquipement(@PathVariable("equipement-id") Long e, @PathVariable("vehicule-id") Long v) {
        equipementService.desaffecterEquipementDeVehicule(e, v);
    }
}