package dungeon.point;

import dungeon.Direction;

/**
 * An object representing a point.
 */
public interface Point {

  /**
   * Returns the x.
   *
   * @return the x
   */
  int getX();

  /**
   * Returns the y.
   *
   * @return the y
   */
  int getY();

  /**
   * Returns a new point with the coordinates of this point + the input ones.
   *
   * @param x the x to add
   * @param y the y to add
   * @return the point
   */
  Point move(int x, int y);

  /**
   * Returns a new point with the coordinates of this point + the input point
   * ones.
   *
   * @param point the input point
   * @return the point
   */
  Point move(Point point);

  /**
   * Return a new point with the coordinates of this point * -1.
   *
   * @return the point
   */
  Point invert();

  /**
   * Returns the direction to the input point.
   *
   * @param point the point
   * @return the direction
   */
  Direction getDirection(Point point);
}
