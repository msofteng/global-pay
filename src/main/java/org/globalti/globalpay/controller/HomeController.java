package org.globalti.globalpay.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.*;

@RestController
@RequestMapping("home")
public class HomeController {
  @GetMapping("users")
  public List<User> listarUsuarios() {
    return List.of(
      new User("Mateus", 22),
      new User("Ricardo", 31)
    );
  }

  @Data
  @AllArgsConstructor
  private class User {
    private String nome;
    private Integer idade;
  }
}