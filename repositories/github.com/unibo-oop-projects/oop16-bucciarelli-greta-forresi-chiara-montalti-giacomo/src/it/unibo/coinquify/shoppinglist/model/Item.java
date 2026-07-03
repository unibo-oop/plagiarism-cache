package it.unibo.coinquify.shoppinglist.model;

/**
 * item interface.
 */

public interface Item {

    /**
     * @return the Item
     */
    String getItem();

    /**
     * @return Quantity
     */
    String getQuantity();

    /**
     * @param item 
     */
    void setItem(String item);

    /**
     * @param quantity 
     *
     */
    void setQuantity(String quantity);

    /**
     * @return the String of the Item
     */

    String toString();
}
