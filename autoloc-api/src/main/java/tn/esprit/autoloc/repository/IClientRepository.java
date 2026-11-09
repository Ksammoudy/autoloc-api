package tn.esprit.autoloc.repository;

import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface IClientRepository extends JpaRepository<Client, Long> {
}