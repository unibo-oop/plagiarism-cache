package dungeon.room;

import dungeon.point.Point;

/**
 * An object representing a {@code Floor} room.
 */
public interface Room {

  /**
   * Returns the room base.
   *
   * @return the point
   */
  Point getBase();

  /**
   * Returns the room size.
   *
   * @return the point
   */
  Point getSize();

  /**
   * Checks if the input point is inside the room.
   *
   * @param point the point
   * @return true, if contains point
   */
  boolean contains(Point point);
}
