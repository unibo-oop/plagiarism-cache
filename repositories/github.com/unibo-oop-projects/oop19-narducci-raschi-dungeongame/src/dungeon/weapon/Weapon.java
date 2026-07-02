package dungeon.weapon;

import dungeon.attack.Attack;
import dungeon.item.Item;
import dungeon.point.Point;
import java.util.Optional;

/**
 * An object representing a weapon.
 */
public interface Weapon extends Item {

  /**
   * Returns the range.
   *
   * @return the range
   */
  int getRange();

  /**
   * Returns the ammo.
   *
   * @return the empty {@code Optional} if it doesn't support ammo, otherwise
   *         the full one with the value
   */
  Optional<Integer> getAmmo();

  /**
   * Sets the extra damage for next hits.
   *
   * @param damage the new value
   */
  void setExtraDamage(int damage);

  /**
   * Hit.
   *
   * @param striker the striker name
   * @param from the source point
   * @param to the target point
   * @return the attack
   */
  Attack hit(String striker, Point from, Point to);
}
