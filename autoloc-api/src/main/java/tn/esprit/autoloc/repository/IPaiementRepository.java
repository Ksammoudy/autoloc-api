package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.autoloc.domain.Paiement;

import java.math.BigDecimal;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("select sum(p.montant) from Paiement p where p.contrat.idContrat = :idContrat")
    BigDecimal totalPayeParContrat(@Param("idContrat") Long idContrat);
}