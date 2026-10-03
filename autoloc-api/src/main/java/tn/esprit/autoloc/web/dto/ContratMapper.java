package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Contrat;

@Component
public class ContratMapper {

    public ContratDTO toDto(Contrat contrat) {
        Long idReservation = contrat.getReservation() != null
                ? contrat.getReservation().getIdReservation() : null;
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateSignature(),
                contrat.getMontantTotal(),
                contrat.isValide(),
                idReservation);
    }
}