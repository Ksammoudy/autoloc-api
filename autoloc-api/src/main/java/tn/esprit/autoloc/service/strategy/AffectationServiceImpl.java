package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IEmployeRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AffectationServiceImpl implements IAffectationService {

    private final IVehiculeRepository vehiculeRepository;
    private final IAgenceRepository agenceRepository;
    private final IEquipementRepository equipementRepository;
    private final IEmployeRepository employeRepository;

    public AffectationServiceImpl(IVehiculeRepository vehiculeRepository,
                                  IAgenceRepository agenceRepository,
                                  IEquipementRepository equipementRepository,
                                  IEmployeRepository employeRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
        this.employeRepository = employeRepository;
    }

    // ---------- Véhicule <-> Agence ----------

    @Override
    @Transactional
    public Vehicule affecterVehiculeAAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = trouverVehicule(idVehicule);
        Agence nouvelle = trouverAgence(idAgence);
        verifierNonLoue(vehicule);

        Agence ancienne = vehicule.getAgence();
        if (ancienne != null) {
            if (ancienne.getIdAgence().equals(idAgence)) {
                throw new IllegalStateException("Le véhicule est déjà affecté à cette agence");
            }
            ancienne.retirerVehicule(vehicule);
        }
        nouvelle.ajouterVehicule(vehicule);
        return vehicule;
    }

    @Override
    @Transactional
    public void desaffecterVehiculeDeAgence(Long idVehicule) {
        Vehicule vehicule = trouverVehicule(idVehicule);
        verifierNonLoue(vehicule);

        Agence agence = vehicule.getAgence();
        if (agence == null) {
            throw new IllegalStateException("Le véhicule n'est affecté à aucune agence");
        }
        agence.retirerVehicule(vehicule);
    }

    // ---------- Véhicule <-> Equipement ----------

    @Override
    @Transactional
    public void ajouterEquipementAuVehicule(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = trouverVehicule(idVehicule);
        Equipement equipement = trouverEquipement(idEquipement);

        if (vehicule.getEquipements().contains(equipement)) {
            throw new IllegalStateException("Cet équipement est déjà présent sur le véhicule");
        }
        vehicule.ajouterEquipement(equipement);
    }

    @Override
    @Transactional
    public void retirerEquipementDuVehicule(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = trouverVehicule(idVehicule);
        Equipement equipement = trouverEquipement(idEquipement);

        if (!vehicule.getEquipements().contains(equipement)) {
            throw new IllegalStateException("Cet équipement n'est pas présent sur le véhicule");
        }
        vehicule.retirerEquipement(equipement);
    }

    // ---------- Employé <-> Agence ----------

    @Override
    @Transactional
    public Employe affecterEmployeAAgence(Long idAgence, Long idEmploye) {
        Agence nouvelle = trouverAgence(idAgence);
        Employe employe = trouverEmploye(idEmploye);

        Agence ancienne = employe.getAgence();
        if (ancienne != null) {
            if (ancienne.getIdAgence().equals(idAgence)) {
                throw new IllegalStateException("L'employé est déjà affecté à cette agence");
            }
            ancienne.retirerEmploye(employe);
        }
        nouvelle.ajouterEmploye(employe);
        return employe;
    }

    @Override
    @Transactional
    public void desaffecterEmployeDeAgence(Long idAgence, Long idEmploye) {
        Agence agence = trouverAgence(idAgence);
        Employe employe = trouverEmploye(idEmploye);

        if (employe.getAgence() == null || !employe.getAgence().getIdAgence().equals(idAgence)) {
            throw new IllegalStateException("L'employé n'est pas affecté à cette agence");
        }
        agence.retirerEmploye(employe);
    }

    // ---------- Consultations (collections LAZY lues dans la transaction) ----------

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> listerEquipementsDuVehicule(Long idVehicule) {
        return List.copyOf(trouverVehicule(idVehicule).getEquipements());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> listerEmployesDeAgence(Long idAgence) {
        return List.copyOf(trouverAgence(idAgence).getEmployes());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> listerVehiculesDeAgence(Long idAgence) {
        return List.copyOf(trouverAgence(idAgence).getVehicules());
    }

    // ---------- Méthodes privées ----------

    private void verifierNonLoue(Vehicule vehicule) {
        if (vehicule.getStatut() == StatutVehicule.LOUE) {
            throw new IllegalStateException("Impossible de modifier l'affectation d'un véhicule actuellement loué");
        }
    }

    private Vehicule trouverVehicule(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Véhicule introuvable, id=" + id));
    }

    private Agence trouverAgence(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Agence introuvable, id=" + id));
    }

    private Equipement trouverEquipement(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipement introuvable, id=" + id));
    }

    private Employe trouverEmploye(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employé introuvable, id=" + id));
    }
}