package dungeon.character.monster;
import dungeon.character.Character;
import dungeon.character.player.Player;
import dungeon.floor.Floor;
import dungeon.item.Item;
import dungeon.point.Point;

/**
 * The Interface Monster.
 */
public interface Monster extends Item {

  /**
   * Sets the floor.
   *
   * @param floor the new floor
   */
  void setFloor(Floor floor);

  /**
   * Gets the breed.
   *
   * @return the breed
   */
  Character getBreed();

  /**
   * Gets the released exp.
   *
   * @return the released exp
   */
  int getReleasedExp();

  /**
   * Take turn.
   *
   * @param player the player
   */
  void takeTurn(Player player);

  /**
   * Gets the spawn.
   *
   * @return the spawn
   */
  Point getSpawn();

  /**
   * Sets the point.
   *
   * @param spawn the new point
   */
  void setPoint(Point spawn);

  /**
   * Sets the random level.
   *
   * @param nLevel the new random level
   */
  void setRandomLevel(int nLevel);
}
