package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Entity(name = "transferencia")
public class TransferenciaEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Schema(hidden = true)
  private Long id;

  private Double valor;

  @Schema(hidden = true)
  private Double valorPercentual;

  @Schema(hidden = true)
  private Double valorOperacao;

  @Column(name = "dt_operacao")
  @Schema(hidden = true)
  private LocalDateTime dataOperacao;

  @Column(name = "dt_agendamento")
  private LocalDateTime dataAgendamento;

  @ManyToOne
  @JoinColumn(name = "origem_id")
  @Schema(implementation = ContaBancaria.class)
  private UsuarioEntity origem;

  @ManyToOne
  @JoinColumn(name = "destino_id")
  @Schema(implementation = ContaBancaria.class)
  private UsuarioEntity destino;

  @ManyToOne
  @JoinColumn(name = "taxa_id")
  @Schema(hidden = true)
  private TaxaEntity taxa;

  @Data
  static class ContaBancaria {
    private Long numeroConta;
  }
}