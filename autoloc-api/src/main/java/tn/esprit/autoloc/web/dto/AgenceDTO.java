package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AgenceDTO(
        Long idAgence,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String nom,

        @NotBlank(message = "La ville est obligatoire")
        @Size(max = 50, message = "La ville ne doit pas dépasser 50 caractères")
        String ville,

        @NotBlank(message = "L'adresse est obligatoire")
        @Size(max = 150, message = "L'adresse ne doit pas dépasser 150 caractères")
        String adresse,

        @NotBlank(message = "Le téléphone est obligatoire")
        @Pattern(regexp = "^[0-9+ ]{8,20}$", message = "Numéro de téléphone invalide")
        String telephone
) {
}