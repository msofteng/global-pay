package org.globalti.globalpay.repository;

import java.time.LocalDate;
import java.util.List;

import org.globalti.globalpay.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> {
  @Query("SELECT t FROM transferencia t WHERE t.origem.id = :usuarioId AND t.dataOperacao BETWEEN :inicio AND :fim")
  List<TransferenciaEntity> findEnviadas(Long usuarioId, LocalDate inicio, LocalDate fim);

  @Query("SELECT t FROM transferencia t WHERE t.destino.id = :usuarioId AND t.dataOperacao BETWEEN :inicio AND :fim")
  List<TransferenciaEntity> findRecebidas(Long usuarioId, LocalDate inicio, LocalDate fim);

  @Query("SELECT t FROM transferencia t WHERE (t.origem.id = :usuarioId OR t.destino.id = :usuarioId) AND t.dataOperacao BETWEEN :inicio AND :fim")
  List<TransferenciaEntity> findExtratoCompleto(Long usuarioId, LocalDate inicio, LocalDate fim);
}