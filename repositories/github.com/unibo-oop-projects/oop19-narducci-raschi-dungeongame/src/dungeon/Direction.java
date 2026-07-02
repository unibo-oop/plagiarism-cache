package dungeon;

import dungeon.point.ImmutablePoint;
import dungeon.point.Point;

/**
 * An object representing a direction of movement.
 */
public enum Direction {

  /** Up. */
  UP(-1, 0),

  /** Left. */
  LEFT(0, -1),

  /** Down. */
  DOWN(1, 0),

  /** Right. */
  RIGHT(0, 1);

  private final Point modifier;

  Direction(final int x, final int y) {
    this.modifier = new ImmutablePoint(x, y);
  }

  /**
   * Returns the point representing the direction modifier.
   *
   * @return the point
   */
  public final Point getModifier() {
    return this.modifier;
  }
}
