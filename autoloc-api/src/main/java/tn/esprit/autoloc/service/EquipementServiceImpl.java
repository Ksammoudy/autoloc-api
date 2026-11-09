package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entity.Contrat;
import tn.esprit.autoloc.entity.Equipement;
import tn.esprit.autoloc.entity.Reservation;
import tn.esprit.autoloc.entity.Vehicule;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;
    private final IVehiculeRepository vehiculeRepository;

    public EquipementServiceImpl(IEquipementRepository equipementRepository,
                                 IVehiculeRepository vehiculeRepository) {
        this.equipementRepository = equipementRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public List<Equipement> listerEquipements() {
        return equipementRepository.findAll();
    }

    @Override


    public void affecterEquipementAVehicule(Long idEquipement, Long idVehicule) {
        Equipement equipement = equipementRepository.findById(idEquipement).orElse(null);
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);

        if (equipement != null && vehicule != null) {
            vehicule.ajouterEquipement(equipement);
            vehiculeRepository.save(vehicule);
        }
    }

    @Override
    public void desaffecterEquipementDeVehicule(Long idEquipement, Long idVehicule) {
        Equipement equipement = equipementRepository.findById(idEquipement).orElse(null);
        Vehicule vehicule = vehiculeRepository.findById(idVehicule).orElse(null);

        if (equipement != null && vehicule != null) {
            vehicule.retirerEquipement(equipement);
            vehiculeRepository.save(vehicule);
        }
    }
}