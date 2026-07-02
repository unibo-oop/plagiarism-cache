package unibo.exiled.model.item;
/**
 * This interface models an object present in the game.
 * An object represents a fundamental element of the game context.
 * Each item has a name and description.
 * Implementing this interface allows you to define specific behaviors
 * for each type of item in the game.
 */

import unibo.exiled.model.item.utilities.ItemType;

/**
 * An item that can be found in-game.
 */
public interface Item {
    /**
     * Returns the name of the item.
     *
     * @return the name of the item.
     */
    String getName();

    /**
     * Returns the description of the item.
     *
     * @return the description of the item.
     */
    String getDescription();

    /**
     * Return the type of the item.
     *
     * @return the type of the item.
     */
    ItemType getType();
}
