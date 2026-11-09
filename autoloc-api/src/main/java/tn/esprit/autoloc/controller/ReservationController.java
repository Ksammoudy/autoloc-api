package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entity.Reservation; // adapte le package de ton entité
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    IReservationService reservationService;

    // http://localhost:8089/autoloc/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.listerReservations();
    }

    // http://localhost:8089/autoloc/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.consulterReservation(reservationId);
    }

    // http://localhost:8089/autoloc/reservation/add-reservation?idClient=1&idVehicule=2
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r,
                                      @RequestParam Long idClient,
                                      @RequestParam Long idVehicule) {
        return reservationService.creerReservation(r, idClient, idVehicule);
    }

    // http://localhost:8089/autoloc/reservation/confirm-reservation/8
    @PutMapping("/confirm-reservation/{reservation-id}")
    public Reservation confirmReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.confirmerReservation(reservationId);
    }

    // http://localhost:8089/autoloc/reservation/cancel-reservation/8
    @PutMapping("/cancel-reservation/{reservation-id}")
    public void cancelReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.annulerReservation(reservationId);
    }
    @PutMapping("/affecter-client/{client-id}/reservation/{reservation-id}")
    public void affecterClient(@PathVariable("client-id") Long c, @PathVariable("reservation-id") Long r) {
        reservationService.affecterClientAReservation(c, r);
    }
}