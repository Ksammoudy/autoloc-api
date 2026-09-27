package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.IReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final ReservationNotifier reservationNotifier;

    public ReservationServiceImpl(IReservationRepository reservationRepository,
                                  ReservationNotifier reservationNotifier) {
        this.reservationRepository = reservationRepository;
        this.reservationNotifier = reservationNotifier;
    }

    @Override
    public Reservation creerReservation(Reservation reservation) {
        reservation.setStatut(StatutReservation.EN_ATTENTE);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation confirmerReservation(Long id) {
        Reservation reservation = consulterReservation(id);
        reservation.setStatut(StatutReservation.CONFIRMEE);
        Reservation confirmee = reservationRepository.save(reservation);

        reservationNotifier.notifierConfirmation(confirmee);

        return confirmee;
    }

    @Override
    public void annulerReservation(Long id) {
        Reservation reservation = consulterReservation(id);
        reservation.setStatut(StatutReservation.ANNULEE);
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation consulterReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Réservation introuvable, id=" + id));
    }

    @Override
    public List<Reservation> listerReservations() {
        return reservationRepository.findAll();
    }
}