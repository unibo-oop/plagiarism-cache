package model.shop;

/**
 * TODO.
 *
 */
public interface ShopItem {

    /**
     * 
     * @return the name of the item.
     */
    String getName(); 

    /**
     * 
     * @return the price of the item.
     */
    int getPrice(); 

    /**
     * 
     * @return if the item is purchased.
     */
    boolean isPurchased(); 

    /**
     * Allows to buy the item.
     */
    void purchase(); 

}
