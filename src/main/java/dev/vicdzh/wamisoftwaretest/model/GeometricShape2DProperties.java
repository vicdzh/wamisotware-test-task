package dev.vicdzh.wamisoftwaretest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeometricShape2DProperties {

  private GeometricShape2DType type;
  private double area;
  private double perimeter;
}
