package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.*;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Data
@Entity(name = "usuario")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String username;

  @Column(name = "password_hash")
  private String password;

  private Long numeroConta;

  private Double saldo;

  @OneToMany(mappedBy = "origem")
  private List<TransferenciaEntity> transferenciasEnviadas;

  @OneToMany(mappedBy = "destino")
  private List<TransferenciaEntity> transferenciasRecebidas;
}