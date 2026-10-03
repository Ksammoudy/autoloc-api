package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipementDTO(
        Long idEquipement,

        @NotBlank(message = "Le libellé est obligatoire")
        @Size(max = 50, message = "Le libellé ne doit pas dépasser 50 caractères")
        String libelle
) {
}