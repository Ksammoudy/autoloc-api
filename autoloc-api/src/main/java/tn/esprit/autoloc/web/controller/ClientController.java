package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.web.dto.ClientDTO;
import tn.esprit.autoloc.web.dto.ClientMapper;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;
    private final ClientMapper clientMapper;

    public ClientController(IClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> ajouter(@Valid @RequestBody ClientDTO dto) {
        var cree = clientService.ajouterClient(clientMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(cree));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> modifier(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        var modifie = clientService.modifierClient(id, clientMapper.toEntity(dto));
        return ResponseEntity.ok(clientMapper.toDto(modifie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.consulterClient(id)));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> lister() {
        return ResponseEntity.ok(clientService.listerClients().stream().map(clientMapper::toDto).toList());
    }
}