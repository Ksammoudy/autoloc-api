package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final ReservationNotifier reservationNotifier;

    public ReservationServiceImpl(IReservationRepository reservationRepository,
                                  IClientRepository clientRepository,
                                  IVehiculeRepository vehiculeRepository,
                                  ReservationNotifier reservationNotifier) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.reservationNotifier = reservationNotifier;
    }

    @Override
    public Reservation creerReservation(Reservation reservation, Long idClient, Long idVehicule) {
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure ou égale à la date de début");
        }
        reservation.setClient(clientRepository.findById(idClient)
                .orElseThrow(() -> new NoSuchElementException("Client introuvable, id=" + idClient)));
        reservation.setVehicule(vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new NoSuchElementException("Véhicule introuvable, id=" + idVehicule)));
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