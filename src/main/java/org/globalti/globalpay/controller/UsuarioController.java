package org.globalti.globalpay.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("bank")
@Tag(
  name = "Clientes Bancários",
  description = "Operações relacionadas aos nossos clientes"
)
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @PostMapping("user/add")
  public UsuarioEntity cadastrar(@RequestBody UsuarioEntity usuario) {
    UsuarioEntity novoUsuario = usuarioService.salvar(usuario);

    novoUsuario.setPassword(null);
    novoUsuario.setId(null);

    return novoUsuario;
  }

  @GetMapping("user/{idUser}")
  public UsuarioEntity encontrarPeloUsuarioId(@PathVariable String idUser) throws GlobalPayException {
    return usuarioService.buscarPorUsuarioId(idUser);
  }

  @GetMapping("users/{username}")
  public List<UsuarioEntity> encontrarPeloUsername(
    @PathVariable String username,
    @RequestParam String pagina,
    @RequestParam String quantidade
  ) throws GlobalPayException {
    return usuarioService.buscarUsuarios(username, quantidade, pagina);
  }

  @DeleteMapping("user/{idUser}")
  @ResponseStatus(NO_CONTENT)
  public void excluirUsuario(@PathVariable String idUser, HttpServletResponse response) throws GlobalPayException {
    usuarioService.deletar(idUser, response);
  }
}