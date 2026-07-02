package rogue.model.creature;

import rogue.model.items.inventory.Inventory;
import rogue.model.items.Equipment;

/**
 * An interface modeling the player.
 */
public interface Player extends Creature<PlayerLife> {

    /**
     * @return the inventory
     */
    Inventory getInventory();

    /**
     * @return the equipment
     */
    Equipment getEquipment();

}
