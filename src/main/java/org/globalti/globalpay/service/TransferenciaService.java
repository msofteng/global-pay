package org.globalti.globalpay.service;

import static org.springframework.http.HttpStatus.*;
import static org.globalti.globalpay.util.Util.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.globalti.globalpay.dto.ExtratoDTO;
import org.globalti.globalpay.entity.*;
import org.globalti.globalpay.enums.TipoExtratoEnum;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
  @Autowired
  private TransferenciaRepository transferenciaRepository;

  @Autowired
  private TaxaRepository taxaRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public TransferenciaEntity realizarTransferencia(TransferenciaEntity transferencia) throws GlobalPayException {
     // associar a "origem" ao usuário autenticado (spring-security)
    UsuarioEntity origem = usuarioRepository.findByNumeroConta(transferencia.getOrigem().getNumeroConta())
      .orElseThrow(() -> new GlobalPayException("A conta de origem não foi encontrada!", NOT_FOUND));
    UsuarioEntity destino = usuarioRepository.findByNumeroConta(transferencia.getDestino().getNumeroConta())
      .orElseThrow(() -> new GlobalPayException("A conta de destino não foi encontrada!", NOT_FOUND));

    UsuarioEntity origemRef = entityManager.getReference(UsuarioEntity.class, origem.getId());
    UsuarioEntity destinoRef = entityManager.getReference(UsuarioEntity.class, destino.getId());

    transferencia.setOrigem(origemRef);
    transferencia.setDestino(destinoRef);

    transferencia.setDataOperacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

    Long dias = ChronoUnit.DAYS.between(transferencia.getDataOperacao(), transferencia.getDataAgendamento());

    TaxaEntity taxa = taxaRepository.findByDiasInRange(dias.intValue())
      .orElseThrow(() -> new GlobalPayException("Não existe taxa aplicável para essa quantidade de dias.", UNPROCESSABLE_ENTITY));

    transferencia.setTaxa(taxa);

    transferencia.setValorPercentual(toFixed(transferencia.getValor() * (taxa.getTaxa() / 100), 2));
    transferencia.setValorOperacao(toFixed(transferencia.getValor() + taxa.getValor() + transferencia.getValorPercentual(), 2));

    if (origem.getSaldo() < transferencia.getValorOperacao()) {
      throw new GlobalPayException("Saldo insuficiente", UNPROCESSABLE_ENTITY);
    }

    origem.setSaldo(toFixed(origem.getSaldo() - transferencia.getValorOperacao(), 2));
    destino.setSaldo(toFixed(destino.getSaldo() + transferencia.getValor(), 2));

    usuarioRepository.save(origem);
    usuarioRepository.save(destino);

    TransferenciaEntity novaTransferencia = transferenciaRepository.save(transferencia);

    return formatarTransferencia(novaTransferencia, null);
  }

  public List<TransferenciaEntity> consultarTransferencias(ExtratoDTO filtro) throws GlobalPayException {
    // associar a "origem" ao usuário autenticado (spring-security)
    UsuarioEntity origem = usuarioRepository.findByNumeroConta(filtro.getNrConta())
      .orElseThrow(() -> new GlobalPayException("Não foi encontrado nenhuma conta correspondente!", NOT_FOUND));

    if (filtro.getQuantidade() == null) {
      throw new GlobalPayException("A quantidade por página está incorreta ou não foi informada!", BAD_REQUEST);
    }

    if (filtro.getPagina() == null) {
      throw new GlobalPayException("A página está incorreta ou não foi informada!", BAD_REQUEST);
    }

    Pageable paginacao = PageRequest.of(
      filtro.getPagina() - 1,
      filtro.getQuantidade()
    );

    switch (filtro.getTipo()) {
      case ENVIADO:
        return formatarTransferencias(
          transferenciaRepository.findEnviadas(
            origem.getId(),
            filtro.getInicio().atTime(0, 0, 0),
            filtro.getFim().atTime(23, 59, 59),
            paginacao
          ),
          filtro.getTipo()
        );
      case RECEBIDO:
        return formatarTransferencias(
          transferenciaRepository.findRecebidas(
            origem.getId(),
            filtro.getInicio().atTime(0, 0, 0),
            filtro.getFim().atTime(23, 59, 59),
            paginacao
          ),
          filtro.getTipo()
        );
      case COMPLETO:
        return formatarTransferencias(
          transferenciaRepository.findExtratoCompleto(
            origem.getId(),
            filtro.getInicio().atTime(0, 0, 0),
            filtro.getFim().atTime(23, 59, 59),
            paginacao
          ),
          filtro.getTipo()
        );
      default:
        throw new GlobalPayException("Tipo de extrato inválido!", BAD_REQUEST);
    }
  }

  private List<TransferenciaEntity> formatarTransferencias(Page<TransferenciaEntity> transferencias, TipoExtratoEnum tipoExtrato) {
    return transferencias.stream()
      .map(transferencia -> formatarTransferencia(transferencia, tipoExtrato))
      .collect(Collectors.toList());
  }

  private TransferenciaEntity formatarTransferencia(TransferenciaEntity transferencia, TipoExtratoEnum tipoExtrato) {
    transferencia.getOrigem().setTransferenciasEnviadas(null);
    transferencia.getOrigem().setTransferenciasRecebidas(null);
    transferencia.getDestino().setTransferenciasEnviadas(null);
    transferencia.getDestino().setTransferenciasRecebidas(null);
    transferencia.getOrigem().setPassword(null);
    transferencia.getDestino().setId(null);
    transferencia.getDestino().setPassword(null);
    transferencia.getDestino().setSaldo(null);

    try {
      switch (tipoExtrato) {
        case ENVIADO:
          transferencia.setOrigem(null);
          break;
        case RECEBIDO:
          transferencia.setDestino(null);
          break;
        case COMPLETO:
          break;
        default:
          break;
      }
    } catch (NullPointerException e) {}

    return transferencia;
  }
}