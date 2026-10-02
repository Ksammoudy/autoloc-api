package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.web.dto.AgenceDTO;
import tn.esprit.autoloc.web.dto.AgenceMapper;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceService agenceService;
    private final AgenceMapper agenceMapper;

    public AgenceController(IAgenceService agenceService, AgenceMapper agenceMapper) {
        this.agenceService = agenceService;
        this.agenceMapper = agenceMapper;
    }

    @PostMapping
    public ResponseEntity<AgenceDTO> ajouter(@Valid @RequestBody AgenceDTO dto) {
        var creee = agenceService.ajouterAgence(agenceMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceMapper.toDto(creee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenceDTO> modifier(@PathVariable Long id, @Valid @RequestBody AgenceDTO dto) {
        var modifiee = agenceService.modifierAgence(id, agenceMapper.toEntity(dto));
        return ResponseEntity.ok(agenceMapper.toDto(modifiee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenceDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(agenceMapper.toDto(agenceService.consulterAgence(id)));
    }

    @GetMapping
    public ResponseEntity<List<AgenceDTO>> lister() {
        return ResponseEntity.ok(agenceService.listerAgences().stream().map(agenceMapper::toDto).toList());
    }
}