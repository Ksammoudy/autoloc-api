package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Paiement;

import java.time.LocalDate;

@Component
public class PaiementMapper {

    /** Le contrat est résolu par le service à partir de idContrat (URL). */
    public Paiement toEntity(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        paiement.setMontant(dto.montant());
        paiement.setDatePaiement(dto.datePaiement() != null ? dto.datePaiement() : LocalDate.now());
        paiement.setModePaiement(dto.modePaiement());
        return paiement;
    }

    public PaiementDTO toDto(Paiement paiement) {
        Long idContrat = paiement.getContrat() != null ? paiement.getContrat().getIdContrat() : null;
        return new PaiementDTO(
                paiement.getIdPaiement(),
                paiement.getMontant(),
                paiement.getDatePaiement(),
                paiement.getModePaiement(),
                idContrat);
    }
}