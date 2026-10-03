package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final IVehiculeRepository vehiculeRepository;

    public MaintenanceServiceImpl(IMaintenanceRepository maintenanceRepository,
                                  IVehiculeRepository vehiculeRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    @Transactional
    public Maintenance planifierMaintenance(Maintenance maintenance, Long idVehicule) {
        if (maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure ou égale à la date de début");
        }
        Vehicule vehicule = trouverVehicule(idVehicule);
        if (vehicule.getStatut() == StatutVehicule.LOUE) {
            throw new IllegalStateException("Impossible de mettre en maintenance un véhicule actuellement loué");
        }

        maintenance.setVehicule(vehicule);
        Maintenance enregistree = maintenanceRepository.save(maintenance);
        vehicule.setStatut(StatutVehicule.MAINTENANCE);
        return enregistree;
    }

    @Override
    @Transactional
    public Maintenance terminerMaintenance(Long id) {
        Maintenance maintenance = consulterMaintenance(id);

        LocalDate aujourdhui = LocalDate.now();
        if (!aujourdhui.isBefore(maintenance.getDateDebut()) && aujourdhui.isBefore(maintenance.getDateFin())) {
            maintenance.setDateFin(aujourdhui);
        }

        Vehicule vehicule = maintenance.getVehicule();
        if (vehicule != null && vehicule.getStatut() == StatutVehicule.MAINTENANCE) {
            vehicule.setStatut(StatutVehicule.DISPONIBLE);
        }
        return maintenance;
    }

    @Override
    @Transactional(readOnly = true)
    public Maintenance consulterMaintenance(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Maintenance introuvable, id=" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> listerMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> listerMaintenancesDuVehicule(Long idVehicule) {
        trouverVehicule(idVehicule);
        return maintenanceRepository.findByVehicule_IdVehicule(idVehicule);
    }

    private Vehicule trouverVehicule(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Véhicule introuvable, id=" + id));
    }
}