package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import tn.esprit.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

/** idPaiement et idContrat sont en lecture seule : le contrat est donné dans l'URL. */
public record PaiementDTO(
        Long idPaiement,

        @NotNull(message = "Le montant est obligatoire")
        @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être strictement positif")
        @Digits(integer = 8, fraction = 2, message = "Montant invalide (8 chiffres max, 2 décimales)")
        BigDecimal montant,

        @PastOrPresent(message = "La date de paiement ne peut pas être dans le futur")
        LocalDate datePaiement,

        @NotNull(message = "Le mode de paiement est obligatoire")
        ModePaiement modePaiement,

        Long idContrat
) {
}