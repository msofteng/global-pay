package org.globalti.globalpay.controller;

import java.util.List;

import org.globalti.globalpay.dto.ExtratoDTO;
import org.globalti.globalpay.entity.TransferenciaEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("bank")
@Tag(
  name = "Transferências Bancárias",
  description = "Operações relacionadas às transferências bancárias"
)
public class TransferenciaController {
  @Autowired
  private TransferenciaService transferenciaService;

  @PostMapping("transfer")
  public TransferenciaEntity realizarTransferencia(@RequestBody TransferenciaEntity transferencia) throws GlobalPayException {
    return transferenciaService.realizarTransferencia(transferencia);
  }

  @PostMapping("statement")
  public List<TransferenciaEntity> consultarExtrato(@RequestBody ExtratoDTO filtro) throws GlobalPayException {
    return transferenciaService.consultarTransferencias(filtro);
  }
}