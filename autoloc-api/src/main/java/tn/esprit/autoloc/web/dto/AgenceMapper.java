package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;

@Component
public class AgenceMapper {

    public Agence toEntity(AgenceDTO dto) {
        Agence agence = new Agence();
        agence.setNom(dto.nom());
        agence.setVille(dto.ville());
        agence.setAdresse(dto.adresse());
        agence.setTelephone(dto.telephone());
        return agence;
    }

    public AgenceDTO toDto(Agence agence) {
        return new AgenceDTO(
                agence.getIdAgence(),
                agence.getNom(),
                agence.getVille(),
                agence.getAdresse(),
                agence.getTelephone());
    }
}