package dev.vicdzh.wamisoftwaretest.util;

import dev.vicdzh.wamisoftwaretest.model.Point2D;
import dev.vicdzh.wamisoftwaretest.model.Vector2D;
import dev.vicdzh.wamisoftwaretest.model.Vector3D;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtil {

  public static double distance(Point2D a, Point2D b) {
    return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
  }

  public static Vector2D vector2D(Point2D a, Point2D b) {
    return new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());
  }

  public static Vector3D product(Vector2D v1, Vector2D v2) {
    return new Vector3D(0d, 0d, v1.getX() * v2.getY() - v1.getY() * v2.getX());
  }

  public static double length(Vector3D v) {
    return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2));
  }
}
