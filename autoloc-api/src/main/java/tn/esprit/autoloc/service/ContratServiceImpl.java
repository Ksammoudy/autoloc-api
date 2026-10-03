package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final IReservationRepository reservationRepository;
    private final IPaiementRepository paiementRepository;
    private final ITarificationService tarificationService;

    public ContratServiceImpl(IContratRepository contratRepository,
                              IReservationRepository reservationRepository,
                              IPaiementRepository paiementRepository,
                              ITarificationService tarificationService) {
        this.contratRepository = contratRepository;
        this.reservationRepository = reservationRepository;
        this.paiementRepository = paiementRepository;
        this.tarificationService = tarificationService;
    }

    @Override
    @Transactional
    public Contrat genererContrat(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new NoSuchElementException("Réservation introuvable, id=" + idReservation));

        if (reservation.getStatut() != StatutReservation.CONFIRMEE) {
            throw new IllegalStateException("Le contrat ne peut être généré que pour une réservation confirmée");
        }
        if (reservation.getContrat() != null) {
            throw new IllegalStateException("Un contrat existe déjà pour cette réservation");
        }

        Vehicule vehicule = reservation.getVehicule();
        long nbJours = Math.max(1, ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin()));
        BigDecimal montantTotal = tarificationService.calculerTarifTotal(vehicule, (int) nbJours);

        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now());
        contrat.setMontantTotal(montantTotal);
        contrat.setValide(true);
        contrat.setReservation(reservation);
        reservation.setContrat(contrat);

        vehicule.setStatut(StatutVehicule.LOUE);

        return contratRepository.save(contrat);
    }

    @Override
    @Transactional(readOnly = true)
    public Contrat consulterContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrat introuvable, id=" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> listerContrats() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerContrat(Long id) {
        Contrat contrat = consulterContrat(id);
        Reservation reservation = contrat.getReservation();
        if (reservation != null) {
            reservation.setContrat(null);
            Vehicule vehicule = reservation.getVehicule();
            if (vehicule != null && vehicule.getStatut() == StatutVehicule.LOUE) {
                vehicule.setStatut(StatutVehicule.DISPONIBLE);
            }
        }
        contratRepository.delete(contrat); // cascade = ALL : les paiements sont supprimés aussi
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculerMontantPaye(Long idContrat) {
        consulterContrat(idContrat);
        BigDecimal total = paiementRepository.totalPayeParContrat(idContrat);
        return total != null ? total : BigDecimal.ZERO;
    }
}