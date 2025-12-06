package org.globalti.globalpay.service;

import static org.globalti.globalpay.util.Util.generateMD5;
import static org.springframework.http.HttpStatus.*;

import org.globalti.globalpay.dto.LoginDTO;
import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  public UsuarioEntity login(LoginDTO login) throws GlobalPayException {
    UsuarioEntity usuario = usuarioRepository.findByUsername(login.getUsername()).orElseThrow(() -> new GlobalPayException("O usuário não foi encontrado", NOT_FOUND));

    if (!usuario.getPassword().equals(generateMD5(login.getPassword()))) {
      throw new GlobalPayException("Senha incorreta", UNAUTHORIZED);
    }

    usuario.setId(null);
    usuario.setPassword(null);
    // usuario.setNumeroConta(null);
    // usuario.setSaldo(null);
    usuario.setTransferenciasEnviadas(null);
    usuario.setTransferenciasRecebidas(null);

    return usuario;
  }
}