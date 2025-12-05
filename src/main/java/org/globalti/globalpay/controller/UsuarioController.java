package org.globalti.globalpay.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("bank")
@Tag(
  name = "Clientes",
  description = "Operações relacionadas aos nossos clientes"
)
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @PostMapping("user/add")
  @Operation(
    summary = "Cadastrar Cliente",
    description = "Cadastrar um novo cliente"
  )
  public UsuarioEntity cadastrar(@Valid @RequestBody UsuarioEntity usuario) {
    return usuarioService.salvar(usuario);
  }

  @GetMapping("user/{idUser}")
  @Operation(
    summary = "Consultar Cliente",
    description = "Consultar um cliente pelo ID ou nome de usuário"
  )
  public UsuarioEntity encontrarPeloUsuarioId(@PathVariable String idUser) throws GlobalPayException {
    return usuarioService.buscarPorUsuarioId(idUser);
  }

  @GetMapping("users/{username}")
  @Operation(
    summary = "Consultar Clientes",
    description = "Consultar clientes pelo nome de usuário"
  )
  public List<UsuarioEntity> encontrarPeloUsername(
    @PathVariable String username,
    @RequestParam String pagina,
    @RequestParam String quantidade
  ) throws GlobalPayException {
    return usuarioService.buscarUsuarios(username, quantidade, pagina);
  }

  @DeleteMapping("user/{id}")
  @ResponseStatus(NO_CONTENT)
  @Operation(
    summary = "Excluir Cliente",
    description = "Excluir um cliente pelo ID"
  )
  public void excluirUsuario(@PathVariable String id, HttpServletResponse response) throws GlobalPayException {
    usuarioService.deletar(id, response);
  }
}