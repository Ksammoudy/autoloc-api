package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entity.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface IContratRepository extends JpaRepository<Contrat, Long> {
}