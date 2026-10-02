package tn.esprit.autoloc.web.dto;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;

@Component
public class ReservationMapper {

    /** Client et véhicule sont résolus par le service à partir de idClient / idVehicule. */
    public Reservation toEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(dto.dateDebut());
        reservation.setDateFin(dto.dateFin());
        return reservation;
    }

    public ReservationResponseDTO toDto(Reservation reservation) {
        Long idClient = reservation.getClient() != null ? reservation.getClient().getIdClient() : null;
        Long idVehicule = reservation.getVehicule() != null ? reservation.getVehicule().getIdVehicule() : null;
        return new ReservationResponseDTO(
                reservation.getIdReservation(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getStatut(),
                idClient,
                idVehicule);
    }
}