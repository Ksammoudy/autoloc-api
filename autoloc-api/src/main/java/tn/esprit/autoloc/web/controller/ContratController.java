package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.dto.ContratMapper;
import tn.esprit.autoloc.web.dto.SoldeContratDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContratController {

    private final IContratService contratService;
    private final ContratMapper contratMapper;

    public ContratController(IContratService contratService, ContratMapper contratMapper) {
        this.contratService = contratService;
        this.contratMapper = contratMapper;
    }

    @PostMapping("/reservations/{idReservation}/contrat")
    public ResponseEntity<ContratDTO> generer(@PathVariable Long idReservation) {
        var contrat = contratService.genererContrat(idReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratMapper.toDto(contrat));
    }

    @GetMapping("/contrats")
    public ResponseEntity<List<ContratDTO>> lister() {
        return ResponseEntity.ok(contratService.listerContrats().stream().map(contratMapper::toDto).toList());
    }

    @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(contratMapper.toDto(contratService.consulterContrat(id)));
    }

    @GetMapping("/contrats/{id}/solde")
    public ResponseEntity<SoldeContratDTO> solde(@PathVariable Long id) {
        var contrat = contratService.consulterContrat(id);
        BigDecimal paye = contratService.calculerMontantPaye(id);
        return ResponseEntity.ok(new SoldeContratDTO(
                id, contrat.getMontantTotal(), paye, contrat.getMontantTotal().subtract(paye)));
    }

    @DeleteMapping("/contrats/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.noContent().build();
    }
}