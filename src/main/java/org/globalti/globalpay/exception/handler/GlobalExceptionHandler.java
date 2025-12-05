package org.globalti.globalpay.exception.handler;

import static org.springframework.http.HttpStatus.*;
import static org.globalti.globalpay.util.Util.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.globalti.globalpay.exception.GlobalPayException;
import org.springframework.dao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.exc.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

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

    

    if (ex.getRootCause() instanceof MismatchedInputException) {
      MismatchedInputException mismatchedInputException = (MismatchedInputException) ex.getRootCause();

      String fieldName = buildFieldPath(mismatchedInputException.getPath());
      String targetTypeName = mismatchedInputException.getTargetType().getSimpleName().toLowerCase();
      String receivedType = "";

      if (mismatchedInputException instanceof InvalidFormatException) {
        InvalidFormatException invalidFormatException = (InvalidFormatException) mismatchedInputException;
        
        receivedType = invalidFormatException.getValue().getClass().getSimpleName().toLowerCase();
      }

      else {
        JsonParser parser = (JsonParser) mismatchedInputException.getProcessor();
        JsonToken token = parser.getCurrentToken();

        receivedType = translateToken(token);
      }

      if (
        message.contains("org.globalti.globalpay.enums.TipoExtratoEnum")
      ) {
        targetTypeName = "ENVIADO, COMPLETO ou RECEBIDO";
      }

      message = String.format(
        "O campo '%s' deve ser do tipo '%s' e não '%s'!",
        fieldName,
        targetTypeName,
        receivedType.toLowerCase()
      );
    }

    if (ex.getRootCause() instanceof DateTimeParseException) {
      DateTimeParseException dtEx = (DateTimeParseException) ex.getRootCause();

      if (ex.getCause() instanceof InvalidFormatException) {
        InvalidFormatException inv = (InvalidFormatException) ex.getCause();
        String fieldName = buildFieldPath(inv.getPath());
        String invalidValue = inv.getValue().toString();

        message = String.format(
          "O campo '%s' deve conter uma data válida e não '%s'!",
          fieldName,
          invalidValue
        );
      } else {
        // fallback genérico (raro de acontecer)
        message = "Formato de data inválido: " + dtEx.getParsedString();
      }
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