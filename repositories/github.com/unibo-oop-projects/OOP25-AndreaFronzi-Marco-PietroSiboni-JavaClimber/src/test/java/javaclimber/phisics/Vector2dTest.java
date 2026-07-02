package javaclimber.phisics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import org.junit.jupiter.api.Test;

/** Test the functionality of the {@link Vector2d} implementation {@link Vector2dImpl}. */
class Vector2dTest {

  private static final double EPSILON = 0.001;

  private static final double X = 10;
  private static final double Y = 20;

  private static final double NEGATIVE_X = -5;
  private static final double NEGATIVE_Y = -15;

  private static final double NEW_X = 5;
  private static final double NEW_Y = 10;

  /** Tests the {@link Vector2d#getX()} method. */
  @Test
  void testGetX() {
    final Vector2d p = new Vector2dImpl(X, Y);
    assertEquals(X, p.getX(), EPSILON);
  }

  /** Tests the {@link Vector2d#getY()} method. */
  @Test
  void testGetY() {
    final Vector2d p = new Vector2dImpl(X, Y);
    assertEquals(Y, p.getY(), EPSILON);
  }

  /** Test for negative coordinates. */
  @Test
  void testNegativeCoordinates() {
    final Vector2d p = new Vector2dImpl(NEGATIVE_X, NEGATIVE_Y);
    assertEquals(NEGATIVE_X, p.getX(), EPSILON);
    assertEquals(NEGATIVE_Y, p.getY(), EPSILON);
  }

  /** Test for zero coordinates. */
  @Test
  void testZeroCoordinates() {
    final Vector2d p = new Vector2dImpl(0, 0);
    assertEquals(0, p.getX(), EPSILON);
    assertEquals(0, p.getY(), EPSILON);
  }

  /** Tests the {@link Vector2d#setX(double)} method. */
  @Test
  void testSetX() {
    final Vector2d p = new Vector2dImpl(X, Y);
    p.setX(NEW_X);
    assertEquals(NEW_X, p.getX(), EPSILON);
  }

  /** Tests the {@link Vector2d#setY(double)} method. */
  @Test
  void testSetY() {
    final Vector2d d = new Vector2dImpl(X, Y);
    d.setY(NEW_Y);
    assertEquals(NEW_Y, d.getY(), EPSILON);
  }
}
