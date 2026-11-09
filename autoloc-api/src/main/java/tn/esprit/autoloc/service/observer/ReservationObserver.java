package tn.esprit.autoloc.service.observer;

import tn.esprit.autoloc.entity.Reservation;

public interface ReservationObserver {
    void onReservationConfirmee(Reservation reservation);
}