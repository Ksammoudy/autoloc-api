package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.EquipementMapper;
import tn.esprit.autoloc.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final IEquipementService equipementService;
    private final EquipementMapper equipementMapper;

    public EquipementController(IEquipementService equipementService, EquipementMapper equipementMapper) {
        this.equipementService = equipementService;
        this.equipementMapper = equipementMapper;
    }

    @PostMapping
    public ResponseEntity<EquipementDTO> ajouter(@Valid @RequestBody EquipementDTO dto) {
        var cree = equipementService.ajouterEquipement(equipementMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementMapper.toDto(cree));
    }

    @GetMapping
    public ResponseEntity<List<EquipementDTO>> lister() {
        return ResponseEntity.ok(equipementService.listerEquipements().stream().map(equipementMapper::toDto).toList());
    }
}