package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Entity(name = "usuario")
@JsonInclude(NON_NULL)
@NoArgsConstructor
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Schema(hidden = true)
  private Long id;

  private String fullName;

  @Column(unique = true)
  private String username;

  @Column(name = "password_hash")
  private String password;

  @Schema(hidden = true)
  private Long numeroConta;

  @Schema(hidden = true)
  private Double saldo;

  @OneToMany(mappedBy = "origem")
  @Schema(hidden = true)
  private List<TransferenciaEntity> transferenciasEnviadas;

  @OneToMany(mappedBy = "destino")
  @Schema(hidden = true)
  private List<TransferenciaEntity> transferenciasRecebidas;

  public UsuarioEntity(String fullName, String username, String password, Long numeroConta, Double saldo) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.numeroConta = numeroConta;
    this.saldo = saldo;
  }
}