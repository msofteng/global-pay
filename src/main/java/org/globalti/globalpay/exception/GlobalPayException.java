package org.globalti.globalpay.exception;

import org.springframework.http.HttpStatus;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalPayException extends Exception {
  private HttpStatus status;

  public GlobalPayException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}