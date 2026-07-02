package it.unibo.model.gameobj.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract game object with a two-dimensional position and a two-dimensional
 * size.
 * This class provides common functionalities to manage the position
 * of an object in a Cartesian coordinate system.
 */
public abstract class AbstractGameObj implements GameObject {

  /**
   * The width of the object.
   */
  private final double width;

  /**
   * The height of the object.
   */
  private final double height;

  /**
   * The two-dimensional position of the object.
   */
  private final Vector2d position;

  /**
   * Constructs a new AbstractGameObj with the specified two-dimensional position.
   *
   * @param height   the height of the game object
   * @param width    the width of the game object
   * @param position the initial position of the game object,
   *                 provided as a {@link Vector2d} instance
   */
  public AbstractGameObj(final double height, final double width, final Vector2d position) {
    this.height = height;
    this.width = width;
    this.position = new Vector2dImpl(position.getX(), position.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getPosX() {
    return this.position.getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getPosY() {
    return this.position.getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Returns the internal position object.
   *
   * @return the {@link Vector2d} representing the position
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The position is mutable and needs to be "
      + "accessed directly for updates.")
  public Vector2d getPosition() {
    return this.position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosition(final Vector2d position) {
    this.position.setX(position.getX());
    this.position.setY(position.getY());
  }
}
