package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Paiement; // adapte le package de ton entité
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
public class PaiementController {

    IPaiementService paiementService;

    // http://localhost:8089/autoloc/paiement/add-paiement/3
    @PostMapping("/add-paiement/{contrat-id}")
    public Paiement addPaiement(@PathVariable("contrat-id") Long contratId,
                                @RequestBody Paiement p) {
        return paiementService.ajouterPaiement(contratId, p);
    }

    // http://localhost:8089/autoloc/paiement/retrieve-paiements-contrat/3
    @GetMapping("/retrieve-paiements-contrat/{contrat-id}")
    public List<Paiement> getPaiementsDuContrat(@PathVariable("contrat-id") Long contratId) {
        return paiementService.listerPaiementsDuContrat(contratId);
    }
    @PutMapping("/affecter-paiement/{paiement-id}/contrat/{contrat-id}")
    public void affecterPaiement(@PathVariable("paiement-id") Long p, @PathVariable("contrat-id") Long c) {
        paiementService.affecterPaiementAContrat(p, c);
    }
}