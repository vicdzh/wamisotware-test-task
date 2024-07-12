package dev.vicdzh.wamisoftwaretest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class GeometricShape2D {

  @Size(min = 3, message = "At least three points have to be provided")
  private final List<@NotNull @Valid Point2D> points;

  @JsonCreator
  public GeometricShape2D(List<@NotNull @Valid Point2D> points) {
    this.points = points;
  }
}
