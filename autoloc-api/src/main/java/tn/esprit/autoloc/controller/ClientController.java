package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Client; // adapte le package de ton entité
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    IClientService clientService;

    // http://localhost:8089/autoloc/client/retrieve-all-clients
    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        return clientService.listerClients();
    }

    // http://localhost:8089/autoloc/client/retrieve-client/8
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.consulterClient(clientId);
    }

    // http://localhost:8089/autoloc/client/add-client
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client c) {
        return clientService.ajouterClient(c);
    }

    // http://localhost:8089/autoloc/client/remove-client/8
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.supprimerClient(clientId);
    }

    // http://localhost:8089/autoloc/client/modify-client/8
    @PutMapping("/modify-client/{client-id}")
    public Client modifyClient(@PathVariable("client-id") Long clientId, @RequestBody Client c) {
        return clientService.modifierClient(clientId, c);
    }
}