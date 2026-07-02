package dungeon.character.player;

import java.util.Optional;

import dungeon.character.Character;
import dungeon.character.stats.Stats;
import dungeon.floor.Floor;
import dungeon.item.Item;
import dungeon.point.Point;
import dungeon.weapon.Weapon;

/**
 * The Interface Player.
 */
public interface Player extends Item {

  /**
   * Gets the point.
   *
   * @return the point
   */
  Point getPoint();

  /**
   * Sets the floor.
   *
   * @param floor the new floor
   */
  void setFloor(Floor floor);

  /**
   * Sets the point.
   *
   * @param point the new point
   */
  void setPoint(Point point);

  /**
   * Move.
   *
   * @param to the to
   * @return the optional
   */
  Optional<Floor> move(Point to);

  /**
   * Gets the exp.
   *
   * @return the exp
   */
  int getExp();

  /**
   * Sets the exp.
   *
   * @param exp the new exp
   */
  void setExp(int exp);

  /**
   * Gets the weapon.
   *
   * @return the weapon
   */
  Weapon getWeapon();

  /**
   * Gets the inventory.
   *
   * @return the inventory
   */
  Inventory getInventory();

  /**
   * Gets the breed.
   *
   * @return the breed
   */
  Character getBreed();

  /**
   * Take item or attack.
   *
   * @param pos the pos
   */
  void takeOrAttack(Point pos);

  /**
   * Change mode.
   *
   * @param mode the mode
   */
  void changeMode(Mode mode);

  /**
   * Level up.
   */
  void levelUp();

  /**
   * Take item.
   *
   * @param i the index
   */
  void takeItem(int i);

  /**
   * Removes the item.
   *
   * @param i the index
   */
  void removeItem(int i);

  /**
   * Gets the stats.
   *
   * @return the stats
   */
  Stats getStats();

  /**
   * Sets the weapon.
   *
   * @param weapon the new weapon
   */
  void setWeapon(Weapon weapon);

  /**
   * Gets the level.
   *
   * @return the level
   */
  int getLevel();
}
