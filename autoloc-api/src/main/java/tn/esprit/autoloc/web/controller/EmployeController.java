package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEmployeService;
import tn.esprit.autoloc.web.dto.EmployeMapper;
import tn.esprit.autoloc.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final IEmployeService employeService;
    private final EmployeMapper employeMapper;

    public EmployeController(IEmployeService employeService, EmployeMapper employeMapper) {
        this.employeService = employeService;
        this.employeMapper = employeMapper;
    }

    @PostMapping
    public ResponseEntity<EmployeDTO> ajouter(@Valid @RequestBody EmployeDTO dto) {
        var cree = employeService.ajouterEmploye(employeMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeMapper.toDto(cree));
    }

    @GetMapping
    public ResponseEntity<List<EmployeDTO>> lister() {
        return ResponseEntity.ok(employeService.listerEmployes().stream().map(employeMapper::toDto).toList());
    }
}