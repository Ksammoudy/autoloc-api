package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.observer.ReservationObserver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationNotifier {

    private final List<ReservationObserver> observers;

    public ReservationNotifier(List<ReservationObserver> observers) {
        this.observers = observers;
    }

    public void notifierConfirmation(Reservation reservation) {
        observers.forEach(observer -> observer.onReservationConfirmee(reservation));
    }
}