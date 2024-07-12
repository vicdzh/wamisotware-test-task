package dev.vicdzh.wamisoftwaretest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.vicdzh.wamisoftwaretest.exception.InvalidGeometricShapeException;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2D;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DProperties;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType;
import dev.vicdzh.wamisoftwaretest.model.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeometricShape2DServiceTest {

  @Autowired private GeometricShape2DService geometricShape2DService;

  @Test
  void givenValidSquareWhenCalculatePropertiesThenExpectValidResult() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 3.0));
    points.add(new Point2D(3.0, 3.0));
    points.add(new Point2D(3.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    GeometricShape2DProperties properties = geometricShape2DService.calculate(geometricShape2D);

    assertEquals(GeometricShape2DType.SQUARE, properties.getType());
    assertEquals(4d, properties.getArea());
    assertEquals(8d, properties.getPerimeter());
  }

  @Test
  void givenValidRectangleWhenCalculatePropertiesThenExpectValidResult() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 3.0));
    points.add(new Point2D(4.0, 3.0));
    points.add(new Point2D(4.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    GeometricShape2DProperties properties = geometricShape2DService.calculate(geometricShape2D);

    assertEquals(GeometricShape2DType.RECTANGLE, properties.getType());
    assertEquals(6d, properties.getArea());
    assertEquals(10d, properties.getPerimeter());
  }

  @Test
  void giveInvalidRectangleWhenCalculatePropertiesThenThrowsException() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 4.0));
    points.add(new Point2D(3.0, 3.0));
    points.add(new Point2D(3.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    assertThrows(
        InvalidGeometricShapeException.class,
        () -> geometricShape2DService.calculate(geometricShape2D));
  }

  @Test
  void givenRightTriangleWhenCalculatePropertiesThenExpectValidResult() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 4.0));
    points.add(new Point2D(5.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    GeometricShape2DProperties properties = geometricShape2DService.calculate(geometricShape2D);

    assertEquals(GeometricShape2DType.TRIANGLE, properties.getType());
    assertEquals(6d, properties.getArea());
    assertEquals(12d, properties.getPerimeter());
  }

  @Test
  void givenRightTriangleWhenCalculateCirclePropertiesThenExpectValidResult() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 4.0));
    points.add(new Point2D(5.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    GeometricShape2DProperties properties =
        geometricShape2DService.calculateCircle(geometricShape2D, true);

    assertEquals(GeometricShape2DType.CIRCLE, properties.getType());
    assertEquals(
        3.14d, new BigDecimal(properties.getArea()).setScale(2, RoundingMode.DOWN).doubleValue());
    assertEquals(
        6.28d,
        new BigDecimal(properties.getPerimeter()).setScale(2, RoundingMode.DOWN).doubleValue());

    properties = geometricShape2DService.calculateCircle(geometricShape2D, false);
    assertEquals(GeometricShape2DType.CIRCLE, properties.getType());
    assertEquals(
        19.63d, new BigDecimal(properties.getArea()).setScale(2, RoundingMode.DOWN).doubleValue());
    assertEquals(
        15.70d,
        new BigDecimal(properties.getPerimeter()).setScale(2, RoundingMode.DOWN).doubleValue());
  }

  @Test
  void givenValidRectangleWhenCalculateCirclePropertiesThenExpectValidResult() {
    List<Point2D> points = new ArrayList<>();
    points.add(new Point2D(1.0, 1.0));
    points.add(new Point2D(1.0, 4.0));
    points.add(new Point2D(5.0, 4.0));
    points.add(new Point2D(5.0, 1.0));

    GeometricShape2D geometricShape2D = new GeometricShape2D(points);

    assertThrows(
        InvalidGeometricShapeException.class,
        () -> {
          geometricShape2DService.calculateCircle(geometricShape2D, true);
        });

    GeometricShape2DProperties properties =
        geometricShape2DService.calculateCircle(geometricShape2D, false);
    assertEquals(GeometricShape2DType.CIRCLE, properties.getType());
    assertEquals(
        19.63d, new BigDecimal(properties.getArea()).setScale(2, RoundingMode.DOWN).doubleValue());
    assertEquals(
        15.70d,
        new BigDecimal(properties.getPerimeter()).setScale(2, RoundingMode.DOWN).doubleValue());
  }
}
