package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IContratRepository contratRepository;

    public PaiementServiceImpl(IPaiementRepository paiementRepository,
                               IContratRepository contratRepository) {
        this.paiementRepository = paiementRepository;
        this.contratRepository = contratRepository;
    }

    @Override
    @Transactional
    public Paiement ajouterPaiement(Long idContrat, Paiement paiement) {
        Contrat contrat = trouverContrat(idContrat);

        if (!contrat.isValide()) {
            throw new IllegalStateException("Le contrat n'est pas valide");
        }

        BigDecimal dejaPaye = paiementRepository.totalPayeParContrat(idContrat);
        if (dejaPaye == null) {
            dejaPaye = BigDecimal.ZERO;
        }
        BigDecimal reste = contrat.getMontantTotal().subtract(dejaPaye);
        if (paiement.getMontant().compareTo(reste) > 0) {
            throw new IllegalStateException("Le montant dépasse le reste à payer (" + reste + ")");
        }

        contrat.ajouterPaiement(paiement);
        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> listerPaiementsDuContrat(Long idContrat) {
        return List.copyOf(trouverContrat(idContrat).getPaiements());
    }

    private Contrat trouverContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrat introuvable, id=" + id));
    }
}