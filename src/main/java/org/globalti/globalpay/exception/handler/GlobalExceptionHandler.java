package org.globalti.globalpay.exception.handler;

import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import org.globalti.globalpay.exception.GlobalPayException;
import org.springframework.dao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    String message = ex.getMessage();

    if (
      message.contains("org.globalti.globalpay.enums.TipoExtratoEnum") &&
      message.contains("not one of the values accepted for Enum class")
    ) {
      message = "O tipo de extrato não é válido!";
    }

    return ResponseEntity.status(BAD_REQUEST)
      .body(
        new GlobalExceptionHandler.Error(
          List.of(
            message
          )
        )
      );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GlobalExceptionHandler.Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(
        new GlobalExceptionHandler.Error(
          ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> String.format("O campo '%s' %s", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList())
        )
      );
  }

  @Data
  @AllArgsConstructor
  static class Error {
    private List<String> errors;
  }
}