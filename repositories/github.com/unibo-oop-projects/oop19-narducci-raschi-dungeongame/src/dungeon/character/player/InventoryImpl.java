package dungeon.character.player;

import java.util.ArrayList;
import java.util.List;

import dungeon.weapon.Weapon;
/**
 * An implementation of {@code Inventory}.
 */
public final class InventoryImpl implements Inventory {

  /** The weapon inventory. */
  private List<Weapon> weaponInventory;

  /** The gold. */
  private int gold;

  /** The Constant BAG_SIZE. */
  public static final int BAG_SIZE = 4;

  /** The is open. */
  private boolean isOpen;

  /**
   * Instantiates a new inventory impl.
   */
  public InventoryImpl() {
    weaponInventory = new ArrayList<>();
    gold = 0;
    isOpen = false;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void setOpen() {
    isOpen = !(isOpen);
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public boolean checkSize() {
    if (this.getNelement() >= InventoryImpl.BAG_SIZE) {
      return false;
    }
    return true;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeGold(final int goldAdded) {
    gold = gold + goldAdded;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeWeapon(final Weapon weapon) {
    if (this.checkSize()) {
      this.weaponInventory.add(weapon);
      System.out.println("You put " + weapon.getName() + " in the inventory");
      return;
    }
    System.out.println("Unable to put " 
      + weapon.getName() + " in the inventory");
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getNelement() {
    return this.getWeaponInventory().size();
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public List<Weapon> getWeaponInventory() {
    return weaponInventory;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public int getGold() {
    return this.gold;
  }
}
