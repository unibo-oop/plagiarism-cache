package it.unibo.model.physics.platformphysic.impl;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformphysic.api.MovementBehaviour;
import it.unibo.model.world.impl.Boundary;

/**
 * Defines horizontal movement behavior for game objects.
 * This behavior updates the position of an object along the X-axis and handles
 * collisions with specified horizontal boundaries, reversing direction when a
 * boundary is reached.
 */
public class HorizontalMovementBehavior implements MovementBehaviour {

  /**
   * Displacement per unit of time.
   */
  private double ds;

  /**
   * Constructs an instance of {@link HorizontalMovementBehavior} with the
   * specified horizontal
   * displacement speed.
   *
   * @param ds the horizontal displacement speed for the movement behavior
   */
  public HorizontalMovementBehavior(final double ds) {
    this.ds = ds;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePosition(final Vector2d position, final double width, final double height, final double dt,
      final Boundary boundary) {
    double x = position.getX() + (ds * dt);
    if (x < boundary.x0()) {
      x = boundary.x0();
      this.ds = -this.ds;
    } else if (x + width > boundary.x1()) {
      x = boundary.x1() - width;
      this.ds = -this.ds;
    }
    position.setX(x);
  }
}
