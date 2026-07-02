package rogue.model.items;

import rogue.model.Entity;
import rogue.model.creature.Player;

/**
 * An interface modeling a game's Item.
 *
 */
public interface Item extends Entity {

    /**
     * Use the item.
     * @param player on which to apply the Item.
     * @return true if the item was correctly used, false otherwise.
     */
    boolean use(Player player);

}
