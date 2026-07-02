package it.unibo.model.physics.api;

/**
 * Rapresents a two-dimensional vector (Cartesian Coordinates).
 */
public interface Vector2d {

  /**
   * Returns the X component of the vector.
   *
   * @return the X component value
   */
  double getX();

  /**
   * Returns the Y component of the vector.
   *
   * @return the Y component value
   */
  double getY();

  /**
   * Sets the X component of the vector.
   *
   * @param x the new X component value
   */
  void setX(double x);

  /**
   * Sets the Y component of the vector.
   *
   * @param y the new Y component value
   */
  void setY(double y);
}
