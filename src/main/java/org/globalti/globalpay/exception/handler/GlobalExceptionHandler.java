package org.globalti.globalpay.exception.handler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.globalti.globalpay.exception.GlobalPayException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.*;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(GlobalPayException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleGlobalPayException(GlobalPayException ex) {
    return ResponseEntity.status(ex.getStatus())
      .body(
        new GlobalExceptionHandler.Error(
          List.of(
            ex.getMessage()
          )
        )
      );
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    String message = ex.getMessage();
    String rootCauseMessage = ex.getRootCause().getMessage();

    if (rootCauseMessage.contains("Unique index or primary key violation") && rootCauseMessage.contains("USERNAME NULLS FIRST")) {
      message = "Já existe um usuário com esse nome!";
    }

    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
      .body(
        new GlobalExceptionHandler.Error(
          List.of(
            message
          )
        )
      );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleIllegalArgumentException(IllegalArgumentException ex) {
    String message = ex.getMessage();

    if (message.contains("Page index must not be less than zero")) {
      message = "O parâmetro 'pagina' não pode ser menor ou igual a zero!";
    }

    if (message.contains("Page size must not be less than one")) {
      message = "O parâmetro 'quantidade' não pode ser menor ou igual a zero!";
    }

    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
      .body(
        new GlobalExceptionHandler.Error(
          List.of(
            message
          )
        )
      );
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    String message = ex.getMessage();

    if (message.contains("No class org.globalti.globalpay.entity.UsuarioEntity entity with id")) {
      message = "O usuário não foi encontrado!";
    }

    return ResponseEntity.status(NOT_FOUND)
      .body(
        new GlobalExceptionHandler.Error(
          List.of(
            message
          )
        )
      );
  }

  @Data
  @AllArgsConstructor
  static class Error {
    private List<String> errors;
  }
}