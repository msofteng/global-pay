package org.globalti.globalpay.dto;

import java.time.LocalDate;

import org.globalti.globalpay.enums.TipoExtratoEnum;

import lombok.*;

@Data
@AllArgsConstructor
public class ExtratoDTO {
  private Long nrConta;
  private TipoExtratoEnum tipo;
  private LocalDate inicio;
  private LocalDate fim;
  private Integer pagina;
  private Integer quantidade;
}