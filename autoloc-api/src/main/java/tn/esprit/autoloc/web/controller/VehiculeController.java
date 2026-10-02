package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;
import tn.esprit.autoloc.web.dto.VehiculeMapper;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeController(IVehiculeService vehiculeService, VehiculeMapper vehiculeMapper) {
        this.vehiculeService = vehiculeService;
        this.vehiculeMapper = vehiculeMapper;
    }

    @PostMapping
    public ResponseEntity<VehiculeDTO> ajouter(@Valid @RequestBody VehiculeDTO dto) {
        var cree = vehiculeService.ajouterVehicule(vehiculeMapper.toEntity(dto), dto.idAgence());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeMapper.toDto(cree));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeDTO> modifier(@PathVariable Long id, @Valid @RequestBody VehiculeDTO dto) {
        var modifie = vehiculeService.modifierVehicule(id, vehiculeMapper.toEntity(dto), dto.idAgence());
        return ResponseEntity.ok(vehiculeMapper.toDto(modifie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeMapper.toDto(vehiculeService.consulterVehicule(id)));
    }

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> lister() {
        return ResponseEntity.ok(vehiculeService.listerVehicules().stream().map(vehiculeMapper::toDto).toList());
    }
}