package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Client;

import java.time.LocalDate;

@Component
public class ClientMapper {

    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setNom(dto.nom());
        client.setPrenom(dto.prenom());
        client.setEmail(dto.email());
        client.setTelephone(dto.telephone());
        client.setNumPermis(dto.numPermis());
        client.setDateInscription(dto.dateInscription() != null ? dto.dateInscription() : LocalDate.now());
        return client;
    }

    public ClientDTO toDto(Client client) {
        return new ClientDTO(
                client.getIdClient(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getNumPermis(),
                client.getDateInscription());
    }
}