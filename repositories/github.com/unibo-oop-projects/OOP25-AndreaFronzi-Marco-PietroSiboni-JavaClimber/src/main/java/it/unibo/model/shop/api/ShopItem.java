package it.unibo.model.shop.api;

import java.util.Map;

/**
 * Interface representing an item available in the shop.
 */
public interface ShopItem {

    /**
     * Get the unique identifier of the shop item.
     * 
     * @return the ID of the shop item
     */
    String getId();

    /**
     * Get the name of the shop item.
     * 
     * @return the name of the shop item
     */
    String getName();

    /**
     * Get the description of the shop item.
     * 
     * @return the description of the shop item
     */
    String getDescription();

    /**
     * Get the price of the shop item.
     * 
     * @return the price of the shop item
     */
    int getPrice();

    /**
     * Get the type of the shop item.
     * 
     * @return the type of the shop item
     */
    ShopItemType getType();

    /**
     * Get the statistics boosted by the shop item.
     * 
     * @return a map of statistics and their corresponding boost values
     */
    Map<ShopItemStat, Double> getStats();

    /**
     * Get the initial duration of the item in matches.
     * 
     * @return the number of matches the effect lasts (0 for permanent items)
     */
    int getInitialDuration();
}
