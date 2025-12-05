package org.globalti.globalpay.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoExtratoEnum {
  ENVIADO("enviado"),
  RECEBIDO("recebido"),
  COMPLETO("completo");

  public final String descricao;
}