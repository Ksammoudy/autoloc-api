package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.web.dto.MaintenanceMapper;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceController(IMaintenanceService maintenanceService, MaintenanceMapper maintenanceMapper) {
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }

    @PostMapping("/maintenances")
    public ResponseEntity<MaintenanceDTO> planifier(@Valid @RequestBody MaintenanceDTO dto) {
        var creee = maintenanceService.planifierMaintenance(maintenanceMapper.toEntity(dto), dto.idVehicule());
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceMapper.toDto(creee));
    }

    @PutMapping("/maintenances/{id}/terminer")
    public ResponseEntity<MaintenanceDTO> terminer(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceMapper.toDto(maintenanceService.terminerMaintenance(id)));
    }

    @GetMapping("/maintenances/{id}")
    public ResponseEntity<MaintenanceDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceMapper.toDto(maintenanceService.consulterMaintenance(id)));
    }

    @GetMapping("/maintenances")
    public ResponseEntity<List<MaintenanceDTO>> lister() {
        return ResponseEntity.ok(maintenanceService.listerMaintenances()
                .stream().map(maintenanceMapper::toDto).toList());
    }

    @GetMapping("/vehicules/{idVehicule}/maintenances")
    public ResponseEntity<List<MaintenanceDTO>> maintenancesDuVehicule(@PathVariable Long idVehicule) {
        return ResponseEntity.ok(maintenanceService.listerMaintenancesDuVehicule(idVehicule)
                .stream().map(maintenanceMapper::toDto).toList());
    }
}