package org.globalti.globalpay.controller;

import java.util.List;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.globalti.globalpay.exception.GlobalPayException;
import org.globalti.globalpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @PostMapping("user/add")
  public UsuarioEntity cadastrar(@RequestBody UsuarioEntity usuario) {
    return usuarioService.salvar(usuario);
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
  public void excluirUsuario(@PathVariable String idUser) throws GlobalPayException {
    usuarioService.deletar(idUser);
  }
}