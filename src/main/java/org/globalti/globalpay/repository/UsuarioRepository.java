package org.globalti.globalpay.repository;

import java.util.*;

import org.globalti.globalpay.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
  public Optional<UsuarioEntity> findByUsername(String username);

  public Optional<UsuarioEntity> findByNumeroConta(Long numeroConta);

  List<UsuarioEntity> findByUsernameContaining(String username);
}