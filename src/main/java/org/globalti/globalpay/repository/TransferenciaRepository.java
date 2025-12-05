package org.globalti.globalpay.repository;

import java.time.LocalDate;

import org.globalti.globalpay.entity.TransferenciaEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> {
  @Query("SELECT t FROM transferencia t WHERE t.origem.id = :usuarioId AND t.dataOperacao BETWEEN :inicio AND :fim")
  Page<TransferenciaEntity> findEnviadas(@Param("usuarioId") Long usuarioId, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, Pageable pageable);

  @Query("SELECT t FROM transferencia t WHERE t.destino.id = :usuarioId AND t.dataOperacao BETWEEN :inicio AND :fim")
  Page<TransferenciaEntity> findRecebidas(@Param("usuarioId") Long usuarioId, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, Pageable pageable);

  @Query("SELECT t FROM transferencia t WHERE (t.origem.id = :usuarioId OR t.destino.id = :usuarioId) AND t.dataOperacao BETWEEN :inicio AND :fim")
  Page<TransferenciaEntity> findExtratoCompleto(@Param("usuarioId") Long usuarioId, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim, Pageable pageable);
}