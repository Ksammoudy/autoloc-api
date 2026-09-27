package tn.esprit.autoloc.service.observer;

import tn.esprit.autoloc.domain.Reservation;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LogReservationObserver implements ReservationObserver {

    private static final Logger log = LoggerFactory.getLogger(LogReservationObserver.class);

    @Override
    public void onReservationConfirmee(Reservation reservation) {
        log.info("Réservation confirmée : id={}", reservation.getIdReservation());
    }
}