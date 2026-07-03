package it.unibo.coinquify.shoppinglist.model;

import java.io.Serializable;

/**
 * Item Class.
 */
public class ItemImpl implements Item, Serializable {

    private static final long serialVersionUID = -3021894064825965958L;
    private String item;
    private String quantity;

    /**
     * Constructor of event.
     * 
     * @param item
     *            of the item
     * @param quantity
     *            of the item
     *
     */
    public ItemImpl(final String item, final String quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String getItem() {
        return this.item;
    }

    @Override
    public String getQuantity() {
        return this.quantity;
    }

    @Override
    public void setItem(final String item) {
        this.item = item;
    }

    @Override
    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.getItem() + "\n" + this.getQuantity();
    }

}
