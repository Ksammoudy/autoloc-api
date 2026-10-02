package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

@Component
public class VehiculeMapper {

    /** L'agence est résolue par le service à partir de idAgence. */
    public Vehicule toEntity(VehiculeDTO dto) {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation(dto.immatriculation());
        vehicule.setMarque(dto.marque());
        vehicule.setModele(dto.modele());
        vehicule.setCategorie(dto.categorie());
        vehicule.setTarifJournalier(dto.tarifJournalier());
        vehicule.setStatut(dto.statut());
        return vehicule;
    }

    public VehiculeDTO toDto(Vehicule vehicule) {
        Long idAgence = vehicule.getAgence() != null ? vehicule.getAgence().getIdAgence() : null;
        return new VehiculeDTO(
                vehicule.getIdVehicule(),
                vehicule.getImmatriculation(),
                vehicule.getMarque(),
                vehicule.getModele(),
                vehicule.getCategorie(),
                vehicule.getTarifJournalier(),
                vehicule.getStatut(),
                idAgence);
    }
}