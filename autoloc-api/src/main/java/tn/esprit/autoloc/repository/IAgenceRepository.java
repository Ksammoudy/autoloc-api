package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}