package dungeon.logic;

import java.net.URL;
import java.util.Optional;

import dungeon.Direction;
import dungeon.character.player.Mode;
import dungeon.floor.Floor;


/**
 * The Interface Logic.
 */
public interface GameLogic {

  /**
   * Move player.
   *
   * @param direction the direction
   */
  void movePlayer(Direction direction);

  /**
   * Take item or make the player attack.
   *
   * @param direction the direction
   */
  void takeOrAttack(Direction direction);

  /**
   * Sets the player mode.
   *
   * @param mode the new player mode
   */
  void setPlayerMode(Mode mode);

  /**
   * Removes the item in the inventory.
   *
   * @param i the index
   */
  void removeItem(int i);

  /**
   * Take item in the inventory.
   *
   * @param i the index
   */
  void takeItem(int i);

  /**
   * Gets the url image.
   *
   * @param i the index
   * @return the image url
   */
  Optional<URL> getUrl(int i);

  /**
   * Gets the item description.
   *
   * @param i the index
   * @return the item description
   */
  String getItemDescription(int i);

  /**
   * Sets the inventory open.
   */
  void setInventoryOpen();

  /**
   * Create new game.
   *
   * @param name the player name
   * @return the new floor
   */
  Floor createNew(String name);
}
