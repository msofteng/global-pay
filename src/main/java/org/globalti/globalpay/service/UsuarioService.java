package org.globalti.globalpay.service;

import static org.globalti.globalpay.util.Util.*;

import java.util.List;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    return usuarioRepository.findById(id).orElseThrow(() -> new GlobalPayException("O usuário não foi encontrado"));
  }

  public UsuarioEntity buscarPorUsuario(String usuario) throws GlobalPayException {
    return usuarioRepository.findByUsername(usuario).orElseThrow(() -> new GlobalPayException("O usuário não foi encontrado"));
  }

  public List<UsuarioEntity> buscarUsuarios(String username) {
    return usuarioRepository.findByUsernameContaining(username);
  }

  public void deletar(Long id) throws GlobalPayException {
    usuarioRepository.deleteById(id);
  }
}