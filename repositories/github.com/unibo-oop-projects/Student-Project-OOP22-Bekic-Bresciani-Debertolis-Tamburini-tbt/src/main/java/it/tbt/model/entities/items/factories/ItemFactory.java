package it.tbt.model.entities.items.factories;

import java.util.Set;

import it.tbt.model.entities.items.Item;

/**
 * Item Factory.
 * @param <T> generic class that extends Item
 */
public interface ItemFactory<T extends Item> {

    /**
     * Returns a set of items, preferably singletone.
     * @return Set of items
     */
    Set<T> getItems();
}
