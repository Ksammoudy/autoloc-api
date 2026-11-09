package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Agence; // adapte le package de ton entité
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceController {

    IAgenceService agenceService;

    // http://localhost:8089/autoloc/agence/retrieve-all-agences
    @GetMapping("/retrieve-all-agences")
    public List<Agence> getAgences() {
        return agenceService.listerAgences();
    }

    // http://localhost:8089/autoloc/agence/retrieve-agence/8
    @GetMapping("/retrieve-agence/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceService.consulterAgence(agenceId);
    }

    // http://localhost:8089/autoloc/agence/add-agence
    @PostMapping("/add-agence")
    public Agence addAgence(@RequestBody Agence a) {
        return agenceService.ajouterAgence(a);
    }

    // http://localhost:8089/autoloc/agence/remove-agence/8
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.supprimerAgence(agenceId);
    }

    // http://localhost:8089/autoloc/agence/modify-agence/8
    @PutMapping("/modify-agence/{agence-id}")
    public Agence modifyAgence(@PathVariable("agence-id") Long agenceId, @RequestBody Agence a) {
        return agenceService.modifierAgence(agenceId, a);
    }
    // http://localhost:8089/autoloc/agence/affecter-vehicule/2/agence/1
    @PutMapping("/affecter-vehicule/{vehicule-id}/agence/{agence-id}")
    public void affecterVehicule(@PathVariable("vehicule-id") Long vehiculeId,
                                 @PathVariable("agence-id") Long agenceId) {
        agenceService.affecterVehiculeAAgence(vehiculeId, agenceId);
    }

    // http://localhost:8089/autoloc/agence/desaffecter-vehicule/2
    @PutMapping("/desaffecter-vehicule/{vehicule-id}")
    public void desaffecterVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        agenceService.desaffecterVehiculeDAgence(vehiculeId);
    }
}