package org.globalti.globalpay.dto;

import static org.globalti.globalpay.util.Util.*;

import java.time.LocalDate;

import javax.validation.constraints.*;

import org.globalti.globalpay.enums.TipoExtratoEnum;

import lombok.*;

@Data
@AllArgsConstructor
public class ExtratoDTO {
  @NotNull(message = MESSAGE_NULL)
  @Positive(message = MESSAGE_POSITIVE)
  private Long nrConta;
  @NotNull(message = MESSAGE_NULL)
  private TipoExtratoEnum tipo;
  @NotNull(message = MESSAGE_NULL)
  private LocalDate inicio;
  @NotNull(message = MESSAGE_NULL)
  private LocalDate fim;
  @NotNull(message = MESSAGE_NULL)
  @Positive(message = MESSAGE_POSITIVE)
  private Integer pagina;
  @NotNull(message = MESSAGE_NULL)
  @Positive(message = MESSAGE_POSITIVE)
  private Integer quantidade;
}