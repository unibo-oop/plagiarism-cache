package it.unibo.coinquify.shoppinglist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public class ShoppingListImpl implements ShoppingList {

    private final List<Item> listItem;

    /**
     * New Empty ShoppingList.
     */
    public ShoppingListImpl() {
        this.listItem = new ArrayList<>();
    }

    /**
     * New ShoppingList.
     * 
     * @param lItem 
     */
    public ShoppingListImpl(final List<Item> lItem) {
        this.listItem = new ArrayList<>(lItem);
    }

    @Override
    public List<Item> getItems() {
        return this.listItem;
    }

    @Override
    public void addItem(final Item item) {
        this.listItem.add(item);
    }

    @Override
    public void updateItem(final Item item, final ItemImpl itemImpl) {
        this.listItem.add(item);
        this.listItem.remove(itemImpl);
    }

    @Override
    public void remove(final Item item) {
        this.listItem.remove(item);

    }

    @Override
    public void removeAll() {
        final List<Item> res = new ArrayList<>(listItem);
        for (final Item i : res) {
            this.listItem.remove(i);
        }

    }


}
