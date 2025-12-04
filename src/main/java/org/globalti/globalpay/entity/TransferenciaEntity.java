package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.*;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;

@Data
@Entity(name = "transferencia")
public class TransferenciaEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private Double valor;

  private Double valorPercentual;

  private Double valorOperacao;

  @Column(name = "dt_operacao")
  private LocalDateTime dataOperacao;

  @Column(name = "dt_agendamento")
  private LocalDateTime dataAgendamento;

  @OneToOne
  @JoinColumn(name = "origem_id", referencedColumnName = "id")
  private UsuarioEntity origem;

  @OneToOne
  @JoinColumn(name = "destino_id", referencedColumnName = "id")
  private UsuarioEntity destino;

  @OneToOne
  @JoinColumn(name = "taxa_id", referencedColumnName = "id")
  private TaxaEntity taxa;
}