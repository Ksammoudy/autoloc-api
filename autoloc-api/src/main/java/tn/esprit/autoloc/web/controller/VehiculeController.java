package tn.esprit.autoloc.web.controller;

import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<Vehicule> ajouter(@RequestBody Vehicule vehicule) {
        Vehicule cree = vehiculeService.ajouterVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(cree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifier(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifierVehicule(id, vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.consulterVehicule(id));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> lister() {
        return ResponseEntity.ok(vehiculeService.listerVehicules());
    }
}