package dungeon.character.player;

import java.util.List;

import dungeon.weapon.Weapon;

/**
 * The Interface Inventory.
 */
public interface Inventory {

  /**
   * Take gold.
   *
   * @param gold the gold to add
   * 
   */
  void takeGold(int gold);

  /**
   * Take weapon.
   *
   * @param weapon the weapon
   */
  void takeWeapon(Weapon weapon);

  /**
   * Gets the weapon inventory.
   *
   * @return the weapon inventory
   */
  List<Weapon> getWeaponInventory();

  /**
   * Sets inventory open.
   */
  void setOpen();

  /**
   * Checks if the inventory is open.
   *
   * @return true, if is open
   */
  boolean isOpen();

  /**
   * Check size.
   *
   * @return true, if successful
   */
  boolean checkSize();

  /**
   * Gets the nelement in the inventory.
   *
   * @return the nelement
   */
  int getNelement();

  /**
   * Gets the gold.
   *
   * @return the gold
   */
  int getGold();
}
