package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Long idContrat, Paiement paiement);
    List<Paiement> listerPaiementsDuContrat(Long idContrat);
    void affecterPaiementAContrat(Long idPaiement, Long idContrat);
}