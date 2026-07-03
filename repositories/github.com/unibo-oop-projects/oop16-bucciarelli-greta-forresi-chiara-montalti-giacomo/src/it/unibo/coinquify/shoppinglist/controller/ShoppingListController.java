package it.unibo.coinquify.shoppinglist.controller;


import java.util.List;

import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ShoppingList;

/**
 * ShoppingList controllerImpl.
 *
 */
public interface ShoppingListController {

    /**
     * @param item 
     * @param list 
     * @return the Item
     */
    Item addItem(Item item, ShoppingList list);

    /**
     * @param item 
     * @param list 
     */
    void removeItem(Item item, List<Item> list);

}
