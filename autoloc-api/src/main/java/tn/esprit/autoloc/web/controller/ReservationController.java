package tn.esprit.autoloc.web.controller;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> creer(@RequestBody Reservation reservation) {
        Reservation creee = reservationService.creerReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(creee);
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<Reservation> confirmer(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.confirmerReservation(id));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Void> annuler(@PathVariable Long id) {
        reservationService.annulerReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.consulterReservation(id));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> lister() {
        return ResponseEntity.ok(reservationService.listerReservations());
    }
}