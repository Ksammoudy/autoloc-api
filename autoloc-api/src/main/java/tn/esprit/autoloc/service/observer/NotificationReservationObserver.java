package tn.esprit.autoloc.service.observer;

import tn.esprit.autoloc.domain.Reservation;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationReservationObserver implements ReservationObserver {

    private static final Logger log = LoggerFactory.getLogger(NotificationReservationObserver.class);

    @Override
    public void onReservationConfirmee(Reservation reservation) {
        // Simulation : en vrai, on enverrait un email/SMS au client
        log.info("Notification envoyée au client pour la réservation id={}", reservation.getIdReservation());
    }
}