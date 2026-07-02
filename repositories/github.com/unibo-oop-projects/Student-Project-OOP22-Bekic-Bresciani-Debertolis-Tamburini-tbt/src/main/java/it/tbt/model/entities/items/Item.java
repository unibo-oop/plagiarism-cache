package it.tbt.model.entities.items;

import it.tbt.model.entities.Entity;

/**
 * Generic item.
 */
public interface Item extends Entity {

    /**
     * Return the monetary value of the item.
     * @return item's value
     */
    int getValue();
}
