package it.unibo.coinquify.shoppinglist.model;

import java.util.List;

/**
 * Shared ShoppingList.
 */
public interface ShoppingList {
    /**
     * 
     * @return the list of all items
     */
    List<Item> getItems();

    /**
     * Add an item to shopping list.
     * 
     * @param item
     *            to add
     */
    void addItem(Item item);

    /**
     * @param item 
     * @param itemImpl 
     */
    void updateItem(Item item, ItemImpl itemImpl);

    /**
     * @param item 
     */
    void remove(Item item);

    /** 
     * Remove all Elements.
     * 
     */
    void removeAll();

}
