package org.globalti.globalpay.controller;

import javax.validation.Valid;

import org.globalti.globalpay.dto.LoginDTO;
import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("auth")
@Tag(
  name = "Autenticação",
  description = "Operações relacionadas a autenticação"
)
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("login")
  @Operation(
    summary = "Realizar Login",
    description = "Realiza o login do usuário"
  )
  private UsuarioEntity realizarLogin(@Valid @RequestBody LoginDTO login) throws GlobalPayException {
    return authService.login(login);
  }
}