package dev.vicdzh.wamisoftwaretest.controller;

import dev.vicdzh.wamisoftwaretest.dto.ErrorResponse;
import dev.vicdzh.wamisoftwaretest.exception.InvalidGeometricShapeException;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2D;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DProperties;
import dev.vicdzh.wamisoftwaretest.service.GeometricShape2DService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequiredArgsConstructor
public class GeometricShape2DController {

  private final GeometricShape2DService geometricShape2DService;

  @PostMapping("/geometry/2d/calculate")
  public GeometricShape2DProperties calculate(
      @RequestBody @Validated GeometricShape2D geometricShape2D,
      @RequestParam(required = false, defaultValue = "false") Boolean detectCircle,
      @RequestParam(required = false, defaultValue = "false") Boolean isInscribed) {
    if (detectCircle) {
      return geometricShape2DService.calculateCircle(geometricShape2D, isInscribed);
    }
    return geometricShape2DService.calculate(geometricShape2D);
  }

  @RestControllerAdvice
  public static class ControllerAdvice {

    @ExceptionHandler(InvalidGeometricShapeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidGeometricShapeException(InvalidGeometricShapeException e) {
      return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
      String message =
          e.getBindingResult().getFieldErrors().stream()
              .map(FieldError::getDefaultMessage)
              .collect(Collectors.joining(",\n"));
      return new ErrorResponse(message);
    }
  }
}
