package org.globalti.globalpay.service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.globalti.globalpay.dto.ExtratoDTO;
import org.globalti.globalpay.entity.*;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @Autowired
  private TaxaRepository taxaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  public TransferenciaEntity realizarTransferencia(TransferenciaEntity transferencia) throws GlobalPayException {
     // associar a "origem" ao usuário autenticado (spring-security)
    UsuarioEntity origem = usuarioRepository.findByNumeroConta(transferencia.getOrigem().getNumeroConta())
      .orElseThrow(() -> new GlobalPayException("A conta de origem não foi encontrada!"));
    UsuarioEntity destino = usuarioRepository.findByNumeroConta(transferencia.getDestino().getNumeroConta())
      .orElseThrow(() -> new GlobalPayException("A conta de destino não foi encontrada!"));

    if (origem.getSaldo() < transferencia.getValor()) {
      throw new GlobalPayException("Saldo insuficiente");
    }

    transferencia.setDataOperacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

    TaxaEntity taxa = taxaRepository.findByDiasInRange(ChronoUnit.DAYS.between(transferencia.getDataOperacao(), transferencia.getDataAgendamento()))
      .orElseThrow(() -> new GlobalPayException("Não existe taxa aplicável para essa quantidade de dias."));

    transferencia.setTaxa(taxa);

    transferencia.setValorPercentual(transferencia.getValor() * (taxa.getTaxa() / 100));
    transferencia.setValorOperacao(transferencia.getValor() + taxa.getValor() + transferencia.getValorPercentual());

    origem.setSaldo(origem.getSaldo() - transferencia.getValorOperacao());
    destino.setSaldo(destino.getSaldo() + transferencia.getValor());

    usuarioRepository.save(origem);
    usuarioRepository.save(destino);

    return transferenciaRepository.save(transferencia);
  }

  public List<TransferenciaEntity> consultarTransferencias(ExtratoDTO filtro) throws GlobalPayException {
    // associar a "origem" ao usuário autenticado (spring-security)
    UsuarioEntity origem = usuarioRepository.findByNumeroConta(filtro.getNrConta())
      .orElseThrow(() -> new GlobalPayException("Não foi encontrado nenhuma conta correspondente!"));

    switch (filtro.getTipo()) {
      case ENVIADO:
        return transferenciaRepository.findEnviadas(origem.getId(), filtro.getInicio(), filtro.getFim());
      case RECEBIDO:
        return transferenciaRepository.findRecebidas(origem.getId(), filtro.getInicio(), filtro.getFim());
      case COMPLETO:
        return transferenciaRepository.findExtratoCompleto(origem.getId(), filtro.getInicio(), filtro.getFim());
      default:
        throw new GlobalPayException("Tipo de extrato inválido!");
    }
  }
}