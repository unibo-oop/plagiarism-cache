package it.tbt.model.entities.npc.api;

import it.tbt.model.entities.items.Item;

import java.util.Map;

/**
 * The {@code ItemNPC} interface represents a non-player character (NPC) that provides items.
 * It extends the {@link NPC} interface and provides a method to retrieve the available items.
 */
public interface ItemNPC extends NPC {

    /**
     * Gets the map of available items provided by the NPC.
     * The map keys represent the items, and the values represent the quantity of each item.
     *
     * @return the map of items and their quantities
     */
    Map<Item, Integer> getItems();
}
