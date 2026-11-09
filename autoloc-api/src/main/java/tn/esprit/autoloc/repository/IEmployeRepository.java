package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository


public interface IEmployeRepository extends JpaRepository<Employe, Long> {
}