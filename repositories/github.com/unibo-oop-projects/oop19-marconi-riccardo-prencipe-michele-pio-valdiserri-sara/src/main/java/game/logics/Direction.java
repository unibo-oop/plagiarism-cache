package game.logics;

import javafx.scene.input.KeyCode;

/**
 * Enumeration for direction composed of an x and an y based on where the tile
 * go.
 *
 */
public enum Direction {

  /**
   * This direction moves up a tile.
   */
  UP(0, -1),

  /**
   * This direction moves right a tile.
   */
  RIGHT(1, 0),

  /**
   * This direction moves down a tile.
   */
  DOWN(0, 1),

  /**
   * This direction moves left a tile.
   */
  LEFT(-1, 0);

  private final int y;
  private final int x;

  Direction(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Direction{" + "y=" + y + ", x=" + x + '}' + name();
  }

  public static Direction valueFor(final KeyCode keyCode) {
    return valueOf(keyCode.name());
  }
}
