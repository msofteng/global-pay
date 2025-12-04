package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.*;
import lombok.*;

@Data
@Entity(name = "usuario")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private String username;

  private String passwordHash;

  private Integer numeroConta;
}