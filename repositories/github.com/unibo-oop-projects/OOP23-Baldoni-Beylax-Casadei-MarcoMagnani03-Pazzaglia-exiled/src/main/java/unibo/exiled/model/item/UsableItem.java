package unibo.exiled.model.item;

import unibo.exiled.model.character.player.Player;

/**
 * This interface represent an item tha can be used by the player.
 */
public interface UsableItem extends Item {
    /**
     * Use the item on the specified player.
     *
     * @param player The player on which the item is used.
     */
    void use(Player player);

    /**
     * Get the amount or effectiveness of the item.
     *
     * @return Depends on the specific type of item. For example, for a healing
     *         item, it represents the amount of health restored.
     */
    double getAmount();
}
