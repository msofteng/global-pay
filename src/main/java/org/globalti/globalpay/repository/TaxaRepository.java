package org.globalti.globalpay.repository;

import java.util.Optional;

import org.globalti.globalpay.entity.TaxaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxaRepository extends JpaRepository<TaxaEntity, Long> {
  @Query("SELECT t FROM taxa t WHERE :dias BETWEEN t.minDias AND t.maxDias")
  Optional<TaxaEntity> findByDiasInRange(Long dias);
}