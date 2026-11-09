package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Contrat; // adapte le package de ton entité
import tn.esprit.autoloc.service.IContratService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratController {

    IContratService contratService;

    // http://localhost:8089/autoloc/contrat/generate-contrat/4
    @PostMapping("/generate-contrat/{reservation-id}")
    public Contrat generateContrat(@PathVariable("reservation-id") Long reservationId) {
        return contratService.genererContrat(reservationId);
    }

    // http://localhost:8089/autoloc/contrat/retrieve-all-contrats
    @GetMapping("/retrieve-all-contrats")
    public List<Contrat> getContrats() {
        return contratService.listerContrats();
    }

    // http://localhost:8089/autoloc/contrat/retrieve-contrat/8
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public Contrat retrieveContrat(@PathVariable("contrat-id") Long contratId) {
        return contratService.consulterContrat(contratId);
    }

    // http://localhost:8089/autoloc/contrat/solde-contrat/8
    @GetMapping("/solde-contrat/{contrat-id}")
    public Map<String, Object> soldeContrat(@PathVariable("contrat-id") Long contratId) {
        Contrat contrat = contratService.consulterContrat(contratId);
        BigDecimal paye = contratService.calculerMontantPaye(contratId);
        return Map.of(
                "idContrat", contratId,
                "montantTotal", contrat.getMontantTotal(),
                "montantPaye", paye,
                "solde", contrat.getMontantTotal().subtract(paye));
    }

    // http://localhost:8089/autoloc/contrat/remove-contrat/8
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContrat(@PathVariable("contrat-id") Long contratId) {
        contratService.supprimerContrat(contratId);
    }
    @PutMapping("/affecter-contrat/{contrat-id}/reservation/{reservation-id}")
    public void affecterContrat(@PathVariable("contrat-id") Long c, @PathVariable("reservation-id") Long r) {
        contratService.affecterContratAReservation(c, r);
    }

    @PutMapping("/desaffecter-contrat/reservation/{reservation-id}")
    public void desaffecterContrat(@PathVariable("reservation-id") Long r) {
        contratService.desaffecterContratDeReservation(r);
    }
}