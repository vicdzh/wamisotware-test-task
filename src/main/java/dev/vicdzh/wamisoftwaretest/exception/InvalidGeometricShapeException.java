package dev.vicdzh.wamisoftwaretest.exception;

import lombok.Getter;

@Getter
public class InvalidGeometricShapeException extends RuntimeException {

  private final String message;

  public InvalidGeometricShapeException(String message) {
    super(message);
    this.message = message;
  }
}
