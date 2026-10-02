package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;

    public VehiculeServiceImpl(IVehiculeRepository vehiculeRepository,
                               IAgenceRepository agenceRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule, Long idAgence) {
        vehicule.setAgence(resoudreAgence(idAgence));
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule, Long idAgence) {
        Vehicule existant = consulterVehicule(id);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        existant.setAgence(resoudreAgence(idAgence));
        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule consulterVehicule(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Véhicule introuvable, id=" + id));
    }

    @Override
    public List<Vehicule> listerVehicules() {
        return vehiculeRepository.findAll();
    }

    private Agence resoudreAgence(Long idAgence) {
        if (idAgence == null) {
            return null;
        }
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable, id=" + idAgence));
    }
}