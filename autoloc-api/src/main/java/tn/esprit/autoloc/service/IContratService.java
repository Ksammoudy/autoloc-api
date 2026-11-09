package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Contrat;

import java.math.BigDecimal;
import java.util.List;

public interface IContratService {
    Contrat genererContrat(Long idReservation);
    Contrat consulterContrat(Long id);
    List<Contrat> listerContrats();
    void supprimerContrat(Long id);
    BigDecimal calculerMontantPaye(Long idContrat);
    void affecterContratAReservation(Long idContrat, Long idReservation);
    void desaffecterContratDeReservation(Long idReservation);
}