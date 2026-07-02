package dungeon.item;

import dungeon.Cell;

/**
 * An object representing a dungeon entity.
 */
public interface Item {

  /**
   * Returns the name.
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Checks if is blocking.
   *
   * @return true, if is blocking
   */
  boolean isBlocking();

  /**
   * Returns the cell.
   *
   * @return the cell
   */
  Cell getCell();
}
