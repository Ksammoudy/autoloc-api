package tn.esprit.autoloc.web.controller;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceService agenceService;

    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    public ResponseEntity<Agence> ajouter(@RequestBody Agence agence) {
        Agence creee = agenceService.ajouterAgence(agence);
        return ResponseEntity.status(HttpStatus.CREATED).body(creee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> modifier(@PathVariable Long id, @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.modifierAgence(id, agence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.consulterAgence(id));
    }

    @GetMapping
    public ResponseEntity<List<Agence>> lister() {
        return ResponseEntity.ok(agenceService.listerAgences());
    }
}