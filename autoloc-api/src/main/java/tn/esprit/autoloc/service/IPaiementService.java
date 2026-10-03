package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Long idContrat, Paiement paiement);
    List<Paiement> listerPaiementsDuContrat(Long idContrat);
}