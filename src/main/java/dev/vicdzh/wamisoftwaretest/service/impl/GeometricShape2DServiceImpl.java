package dev.vicdzh.wamisoftwaretest.service.impl;

import static dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType.CIRCLE;
import static dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType.RECTANGLE;
import static dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType.SQUARE;
import static dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType.TRIANGLE;
import static dev.vicdzh.wamisoftwaretest.util.MathUtil.length;
import static dev.vicdzh.wamisoftwaretest.util.MathUtil.product;
import static dev.vicdzh.wamisoftwaretest.util.MathUtil.vector2D;

import dev.vicdzh.wamisoftwaretest.exception.InvalidGeometricShapeException;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2D;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DProperties;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DType;
import dev.vicdzh.wamisoftwaretest.model.Point2D;
import dev.vicdzh.wamisoftwaretest.model.Vector2D;
import dev.vicdzh.wamisoftwaretest.service.GeometricShape2DService;
import dev.vicdzh.wamisoftwaretest.util.MathUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GeometricShape2DServiceImpl implements GeometricShape2DService {

  @Override
  public GeometricShape2DProperties calculate(GeometricShape2D geometricShape2D) {
    List<Point2D> points = geometricShape2D.getPoints();
    double[] distances = calculateDistances(points);
    GeometricShape2DType type = detectType(distances);
    return new GeometricShape2DProperties(
        type, calculateArea(type, points, distances), calculatePerimeter(type, distances));
  }

  @Override
  public GeometricShape2DProperties calculateCircle(
      GeometricShape2D geometricShape2D, boolean isInscribed) {
    List<Point2D> points = geometricShape2D.getPoints();
    double[] distances = calculateDistances(points);
    GeometricShape2DType type = detectType(distances);
    double radius = 0d;
    if (TRIANGLE == type) {
      double triangleArea = calculateArea(TRIANGLE, points, distances);
      if (isInscribed) {
        double trianglePerimeter = calculatePerimeter(TRIANGLE, distances);
        radius = triangleArea / (trianglePerimeter / 2d);
      } else {
        radius = (distances[0] * distances[1] * distances[2]) / (4d * triangleArea);
      }
    } else if (RECTANGLE == type || SQUARE == type) {
      if (isInscribed) {
        if (SQUARE != type) {
          throw new InvalidGeometricShapeException("Circle can be inscribed into square only");
        }
        radius = distances[0] / 2d;
      } else {
        radius = (Math.sqrt(Math.pow(distances[0], 2d) + Math.pow(distances[1], 2d))) / 2d;
      }
    }
    double circleArea = Math.PI * Math.pow(radius, 2d);
    double circlePerimeter = 2d * Math.PI * radius;
    return new GeometricShape2DProperties(CIRCLE, circleArea, circlePerimeter);
  }

  private double[] calculateDistances(List<Point2D> points) {
    double[] distances = new double[points.size()];
    for (int i = 0; i < points.size() - 1; i++) {
      distances[i] = MathUtil.distance(points.get(i), points.get(i + 1));
    }
    distances[points.size() - 1] = MathUtil.distance(points.get(points.size() - 1), points.get(0));
    return distances;
  }

  private GeometricShape2DType detectType(double[] distances) {
    if (distances.length == 3) {
      return TRIANGLE;
    }
    if (distances.length == 4) {
      if (distances[0] == distances[1]) {
        return GeometricShape2DType.SQUARE;
      }
      if (distances[0] == distances[2]) {
        return GeometricShape2DType.RECTANGLE;
      }
    }
    throw new InvalidGeometricShapeException("Provided geometric shape is not supported");
  }

  private double calculateArea(
      GeometricShape2DType type, List<Point2D> points, double[] distances) {
    switch (type) {
      case SQUARE:
      case RECTANGLE:
        return distances[0] * distances[1];
      case TRIANGLE:
        Vector2D v1 = vector2D(points.get(0), points.get(1));
        Vector2D v2 = vector2D(points.get(0), points.get(2));
        return length(product(v1, v2)) / 2d;
    }
    throw new InvalidGeometricShapeException("Provided geometric shape is not supported");
  }

  private double calculatePerimeter(GeometricShape2DType type, double[] distances) {
    switch (type) {
      case SQUARE:
      case RECTANGLE:
        return (distances[0] + distances[1]) * 2d;
      case TRIANGLE:
        return distances[0] + distances[1] + distances[2];
    }
    return 0d;
  }
}
