package it.unibo.coinquify.shoppinglist.controller;


import java.util.List;

import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ShoppingList;

/**
 * SHoppingList Controller Interface.
 *
 */
public class ShoppingListControllerImpl implements ShoppingListController {

    @Override
    public Item addItem(final Item item, final ShoppingList list) {
       list.addItem(item);
        return item;
    }

    @Override
    public void removeItem(final Item item, final List<Item> list) {
       list.remove(item);

    }

}
