package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Equipement;

@Component
public class EquipementMapper {

    public Equipement toEntity(EquipementDTO dto) {
        Equipement equipement = new Equipement();
        equipement.setLibelle(dto.libelle());
        return equipement;
    }

    public EquipementDTO toDto(Equipement equipement) {
        return new EquipementDTO(equipement.getIdEquipement(), equipement.getLibelle());
    }
}