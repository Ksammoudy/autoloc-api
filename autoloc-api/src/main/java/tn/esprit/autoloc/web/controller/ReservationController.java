package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationMapper;
import tn.esprit.autoloc.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc.web.dto.ReservationResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(IReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> creer(@Valid @RequestBody ReservationRequestDTO dto) {
        var creee = reservationService.creerReservation(
                reservationMapper.toEntity(dto), dto.idClient(), dto.idVehicule());
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.toDto(creee));
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<ReservationResponseDTO> confirmer(@PathVariable Long id) {
        return ResponseEntity.ok(reservationMapper.toDto(reservationService.confirmerReservation(id)));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Void> annuler(@PathVariable Long id) {
        reservationService.annulerReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> consulter(@PathVariable Long id) {
        return ResponseEntity.ok(reservationMapper.toDto(reservationService.consulterReservation(id)));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> lister() {
        return ResponseEntity.ok(
                reservationService.listerReservations().stream().map(reservationMapper::toDto).toList());
    }
}