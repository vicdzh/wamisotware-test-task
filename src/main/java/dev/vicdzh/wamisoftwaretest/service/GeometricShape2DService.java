package dev.vicdzh.wamisoftwaretest.service;

import dev.vicdzh.wamisoftwaretest.model.GeometricShape2D;
import dev.vicdzh.wamisoftwaretest.model.GeometricShape2DProperties;

public interface GeometricShape2DService {

  GeometricShape2DProperties calculate(GeometricShape2D geometricShape2D);

  GeometricShape2DProperties calculateCircle(GeometricShape2D geometricShape2D, boolean isInscribed);

}
