package org.globalti.globalpay.exception.handler;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.globalti.globalpay.exception.GlobalPayException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;


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

  @Data
  @AllArgsConstructor
  static class Error {
    private List<String> errors;
  }
}