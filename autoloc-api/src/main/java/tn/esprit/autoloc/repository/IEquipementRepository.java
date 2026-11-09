package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}