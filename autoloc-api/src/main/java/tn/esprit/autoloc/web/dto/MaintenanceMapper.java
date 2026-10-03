package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Maintenance;

@Component
public class MaintenanceMapper {

    /** Le véhicule est résolu par le service à partir de idVehicule. */
    public Maintenance toEntity(MaintenanceDTO dto) {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(dto.dateDebut());
        maintenance.setDateFin(dto.dateFin());
        maintenance.setDescription(dto.description());
        return maintenance;
    }

    public MaintenanceDTO toDto(Maintenance maintenance) {
        Long idVehicule = maintenance.getVehicule() != null
                ? maintenance.getVehicule().getIdVehicule() : null;
        return new MaintenanceDTO(
                maintenance.getIdMaintenance(),
                maintenance.getDateDebut(),
                maintenance.getDateFin(),
                maintenance.getDescription(),
                idVehicule);
    }
}