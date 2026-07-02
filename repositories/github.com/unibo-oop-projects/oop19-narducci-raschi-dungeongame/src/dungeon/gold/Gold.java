package dungeon.gold;

import dungeon.item.Item;

/**
 * An object representing a pile of gold coins.
 */
public interface Gold extends Item {

  /**
   * Returns the amount of gold coins.
   *
   * @return the amount
   */
  int getAmount();
}
