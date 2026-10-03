package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.web.dto.PaiementDTO;
import tn.esprit.autoloc.web.dto.PaiementMapper;

import java.util.List;

@RestController
@RequestMapping("/api/contrats/{idContrat}/paiements")
public class PaiementController {

    private final IPaiementService paiementService;
    private final PaiementMapper paiementMapper;

    public PaiementController(IPaiementService paiementService, PaiementMapper paiementMapper) {
        this.paiementService = paiementService;
        this.paiementMapper = paiementMapper;
    }

    @PostMapping
    public ResponseEntity<PaiementDTO> ajouter(@PathVariable Long idContrat,
                                               @Valid @RequestBody PaiementDTO dto) {
        var cree = paiementService.ajouterPaiement(idContrat, paiementMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementMapper.toDto(cree));
    }

    @GetMapping
    public ResponseEntity<List<PaiementDTO>> lister(@PathVariable Long idContrat) {
        return ResponseEntity.ok(paiementService.listerPaiementsDuContrat(idContrat)
                .stream().map(paiementMapper::toDto).toList());
    }
}