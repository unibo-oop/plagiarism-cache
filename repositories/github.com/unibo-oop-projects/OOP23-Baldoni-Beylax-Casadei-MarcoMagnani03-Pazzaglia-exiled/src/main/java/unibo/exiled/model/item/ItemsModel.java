package unibo.exiled.model.item;

import unibo.exiled.model.item.utilities.ItemType;

import java.util.Map;

/**
 * The model that manages items in the game.
 */
public interface ItemsModel {
    /**
     * Returns the items of the player.
     *
     * @return The items of the player.
     */
    Map<String, Integer> getPlayerItems();

    /**
     * Returns the description of a given item.
     *
     * @param itemName The name of the item.
     * @return the description of a given item.
     */
    String getItemDescription(String itemName);

    /**
     * Returns the valor of a given item.
     *
     * @param itemName The name of the item.
     * @return The valor of the given item.
     */
    double getItemValor(String itemName);

    /**
     * Returns the type of given item.
     *
     * @param itemName The name of the item.
     * @return The type of the given item.
     */
    ItemType getItemType(String itemName);

    /**
     * Returns the boosted attribute name of a given item.
     *
     * @param itemName The name of the item.
     * @return The boosted attribute name of the given item.
     */
    String getItemBoostedAttributeName(String itemName);

    /**
     * Attempts to use the specified item from the player's inventory.
     *
     * @param item The item to use.
     */
    void useItem(String item);
}
