package dungeon.character.player;

import dungeon.character.monster.Monster;
import dungeon.floor.Floor;
import dungeon.point.Point;

/**
 * An implementation of {@code Mode}, make the player grab item.
 */
public final class GrabMode implements Mode {

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void takeAction(final Point pos,
      final Player player, final Floor floor) {
    if (floor.getItem(pos).isPresent() 
        && Monster.class.isAssignableFrom(
            floor.getItem(pos).get().getClass())) {
      return;
    }
    if (floor.getGolds().containsKey(pos)) {
      player.getInventory()
          .takeGold(floor.getGolds().get(pos).getAmount());
      floor.remove(pos);
    } else if (floor.getWeapons().containsKey(pos)
        && player.getInventory().checkSize()) {
      player.getInventory().takeWeapon(floor.getWeapons().get(pos));
      floor.remove(pos);
    } else if (floor.getWeapons().containsKey(pos) 
        && player.getInventory().checkSize()) {
      System.out.println("Inventory is full");
    }
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Grab Mode";
  }
}
