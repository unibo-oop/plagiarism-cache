package javaclimber.phisics.platformphysic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.world.impl.Boundary;

/**
 * Test class for the horizontal movement behavior of game objects.
 * This class ensures that the {@link HorizontalMovementBehavior} functions correctly
 * by simulating various scenarios, including movement across boundaries, and verifying results.
 */
class HorizontalMovementBehaviorTest {

  private static final double EPSILON = 0.001;

  private static final double WIDTH = 10;
  private static final double HEIGHT = 10;

  private static final double INITIAL_Y = 20;
  private static final double INITIAL_X = 10;

  private static final double NEW_X = 60;

  private static final double LEFT_BOUNDARY = 0;
  private static final double RIGHT_BOUNDARY = 100;

  private static final double DELTA_T = 1;
  private static final double DELTA_S_1 = 50;
  private static final double DELTA_S_2 = 300;
  private static final double DELTA_S_3 = -300;

  /**
   * Tests Obj horizontal movement behavior.
   */
  @Test
  void testOrizzontalMovementBehaviour() {
    final Boundary bounds = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    final Vector2d p = new Vector2dImpl(INITIAL_X, INITIAL_Y);
    final MovementBehaviour mb = new HorizontalMovementBehavior(DELTA_S_1);
    mb.updatePosition(p, WIDTH, HEIGHT, DELTA_T, bounds);
    assertEquals(NEW_X, p.getX(), EPSILON);
    assertEquals(INITIAL_Y, p.getY(), EPSILON);
  }

  /**
   * Tests the behavior when the object reaches the right boundary.
   * It verifies that the object's position is correctly clamped within the boundary limits
   * and does not exceed the right edge.
   */
  @Test
  void testRightBourderTouchBeavior() {
    final Boundary bounds = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    final Vector2d p = new Vector2dImpl(INITIAL_X, INITIAL_Y);
    final MovementBehaviour mb = new HorizontalMovementBehavior(DELTA_S_2);
    mb.updatePosition(p, WIDTH, HEIGHT, DELTA_T, bounds);
    assertEquals(RIGHT_BOUNDARY - WIDTH, p.getX(), EPSILON);
    assertEquals(INITIAL_Y, p.getY(), EPSILON);
  }

  /**
   * Tests the behavior when the object reaches the left boundary.
   * It verifies that the object's position is correctly clamped within the boundary limits
   * and does not exceed the left edge.
   */
  @Test
  void testLeftBourderTouchBeavior() {
    final Boundary bounds = new Boundary(LEFT_BOUNDARY, RIGHT_BOUNDARY);
    final Vector2d p = new Vector2dImpl(INITIAL_X, INITIAL_Y);
    final MovementBehaviour mb = new HorizontalMovementBehavior(DELTA_S_3);
    mb.updatePosition(p, WIDTH, HEIGHT, DELTA_T, bounds);
    assertEquals(LEFT_BOUNDARY, p.getX(), EPSILON);
    assertEquals(INITIAL_Y, p.getY(), EPSILON);
  }
}
