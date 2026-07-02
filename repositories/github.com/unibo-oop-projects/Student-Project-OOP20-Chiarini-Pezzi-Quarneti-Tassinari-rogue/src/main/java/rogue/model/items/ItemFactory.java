package rogue.model.items;

import java.util.List;

/**
 * An interface modeling an {@link Item} Factory.
 *
 */
public interface ItemFactory {

    /**
     * Create a list containing random items.
     * @param quantity of wanted items.
     * @return a list of random items.
     */
    List<Item> getItems(int quantity);

    /**
     * Creates a list of random items with a random quantity.
     * @return a list of random items.
     */
    List<Item> getItems();
}
