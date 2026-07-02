package dungeon;

import java.net.URL;
import java.util.Objects;

/**
 * An object representing a dungeon cell.
 */
public enum Cell {

  /** A bow. */
  BOW("bow.png"),

  /** A door. */
  DOOR("door.png"),

  /** An exit. */
  EXIT("exit.png"),

  /** A gold. */
  GOLD("gold.png"),

  /** A mob. */
  MOB("mob.png"),

  /** A player. */
  PLAYER("player.png"),

  /** A sword. */
  SWORD("sword.png"),

  /** A wall. */
  WALL("wall.png"),

  /** A boss. */
  BOSS("boss.png");

  private final URL url;

  Cell(final String path) {
    this.url = Objects.requireNonNull(ClassLoader.getSystemResource(path));
  }

  /**
   * Returns the corresponding image URL.
   *
   * @return the URL
   */
  public final URL getImageURL() {
    return this.url;
  }
}
