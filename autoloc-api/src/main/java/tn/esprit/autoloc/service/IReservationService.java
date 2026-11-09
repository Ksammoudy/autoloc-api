package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation creerReservation(Reservation reservation, Long idClient, Long idVehicule);
    Reservation confirmerReservation(Long id);
    void annulerReservation(Long id);
    Reservation consulterReservation(Long id);
    List<Reservation> listerReservations();
    void affecterClientAReservation(Long idClient, Long idReservation);
}