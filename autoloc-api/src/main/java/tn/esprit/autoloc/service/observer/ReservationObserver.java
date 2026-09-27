package tn.esprit.autoloc.service.observer;

import tn.esprit.autoloc.domain.Reservation;

public interface ReservationObserver {
    void onReservationConfirmee(Reservation reservation);
}