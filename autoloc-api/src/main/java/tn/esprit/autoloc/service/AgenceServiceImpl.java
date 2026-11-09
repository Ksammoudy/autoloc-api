package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Agence;
import tn.esprit.autoloc.entity.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final IVehiculeRepository vehiculeRepository;

    public AgenceServiceImpl(IAgenceRepository agenceRepository,
                             IVehiculeRepository vehiculeRepository) {
        this.agenceRepository = agenceRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Long id, Agence agence) {
        Agence existante = consulterAgence(id);
        existante.setNom(agence.getNom());
        existante.setVille(agence.getVille());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    public void supprimerAgence(Long id) {
        agenceRepository.deleteById(id);
    }

    @Override
    public Agence consulterAgence(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable, id=" + id));
    }

    @Override
    public List<Agence> listerAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public void affecterVehiculeAAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElse(null);
        Agence agence = consulterAgence(idAgence);

        vehicule.setAgence(agence);
        vehiculeRepository.save(vehicule);
    }
    @Override
    public void desaffecterVehiculeDAgence(Long idVehicule) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElse(null);

        vehicule.setAgence(null);
        vehiculeRepository.save(vehicule);
    }
}