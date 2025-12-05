package org.globalti.globalpay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoExtratoEnum {
  ENVIADO("enviado"),
  RECEBIDO("recebido"),
  COMPLETO("completo");

  public final String descricao;
}