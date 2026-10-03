package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Employe;

@Component
public class EmployeMapper {

    public Employe toEntity(EmployeDTO dto) {
        Employe employe = new Employe();
        employe.setNom(dto.nom());
        employe.setPrenom(dto.prenom());
        employe.setRole(dto.role());
        return employe;
    }

    public EmployeDTO toDto(Employe employe) {
        Long idAgence = employe.getAgence() != null ? employe.getAgence().getIdAgence() : null;
        return new EmployeDTO(
                employe.getIdEmploye(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getRole(),
                idAgence);
    }
}