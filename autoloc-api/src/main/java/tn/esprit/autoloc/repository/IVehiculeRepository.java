package tn.esprit.autoloc.repository;

import tn.esprit.autoloc.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {
}