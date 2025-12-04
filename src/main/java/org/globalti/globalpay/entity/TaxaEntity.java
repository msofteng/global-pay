package org.globalti.globalpay.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.*;
import lombok.*;

@Data
@Entity(name = "taxa")
@RequiredArgsConstructor
public class TaxaEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NonNull
  private Integer minDias;

  @NonNull
  private Integer maxDias;

  @NonNull
  private Double valor;

  @NonNull
  private Double taxa;
}