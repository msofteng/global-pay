package org.globalti.globalpay.repository;

import org.globalti.globalpay.entity.TaxaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxaRepository extends JpaRepository<TaxaEntity, Long> {}