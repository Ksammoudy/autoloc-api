package tn.esprit.autoloc.web.controller;

import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> ajouter(@RequestBody Client client) {
        Client cree = clientService.ajouterClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(cree);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modifier(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifierClient(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.consulterClient(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> lister() {
        return ResponseEntity.ok(clientService.listerClients());
    }
}
