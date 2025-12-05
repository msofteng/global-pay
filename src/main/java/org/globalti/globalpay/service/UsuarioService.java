package org.globalti.globalpay.service;

import static org.globalti.globalpay.util.Util.*;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  public UsuarioEntity salvar(UsuarioEntity usuario) {
    usuario.setPassword(generateMD5(usuario.getPassword()));
    usuario.setNumeroConta(generateAccountNumber(10));
    
    return usuarioRepository.save(usuario);
  }

  public UsuarioEntity buscarPorId(Long id) throws GlobalPayException {
    return usuarioRepository.findById(id).orElseThrow(() -> new GlobalPayException("O usuário não foi encontrado", NOT_FOUND));
  }

  public UsuarioEntity buscarPorUsuario(String usuario) throws GlobalPayException {
    return usuarioRepository.findByUsername(usuario).orElseThrow(() -> new GlobalPayException("O usuário não foi encontrado", NOT_FOUND));
  }

  public UsuarioEntity buscarPorUsuarioId(String usuarioId) throws GlobalPayException {
    if (isLong(usuarioId)) {
      return buscarPorId(Long.parseLong(usuarioId));
    } else {
      return buscarPorUsuario(usuarioId);
    }
  }

  public List<UsuarioEntity> buscarUsuarios(String username, String qtd, String page) throws GlobalPayException {
    if (!isInt(qtd)) {
      throw new GlobalPayException("A quantidade por página está incorreta ou não foi informada!", BAD_REQUEST);
    }

    if (!isInt(page)) {
      throw new GlobalPayException("A página está incorreta ou não foi informada!", BAD_REQUEST);
    }

    return usuarioRepository.findByUsernameContaining(
      username,
      PageRequest.of(
        Integer.parseInt(page),
        Integer.parseInt(qtd)
      )
    ).toList();
  }

  public void deletar(String id) throws GlobalPayException {
    if (!isLong(id)) {
      throw new GlobalPayException("O ID deve ser um número!", BAD_REQUEST);
    }

    usuarioRepository.deleteById(Long.parseLong(id));
  }
}