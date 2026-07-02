package dungeon.movelogic;

import dungeon.point.Point;

/**
 * An object that decides how a movable entity can move.
 */
public interface MoveLogic {

  /**
   * Returns the target point for the next move.
   *
   * @return the point
   */
  Point move();
}
