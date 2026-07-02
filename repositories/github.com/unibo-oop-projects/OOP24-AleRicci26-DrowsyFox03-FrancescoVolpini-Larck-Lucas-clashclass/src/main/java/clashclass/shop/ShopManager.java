package clashclass.shop;

import clashclass.resources.ResourceType;

import java.util.List;

/**
 * Represents the in-game shop.
 */
public interface ShopManager {
    /**
     * Given a shopItem return the Balance of its resource.
     *
     * @param shopItem the item we want to know the resource balance
     *
     * @return the current value of the resource as a double
     */
    double getBalance(ShopItem shopItem);

    /**
     * Given a ShopItem says if it is affordable.
     *
     * @param shopItem the item we want to analyze
     *
     * @return  the answer as a boolean
     */
    boolean canAfford(ShopItem shopItem);

    /**
     * Allow us to buy a ShopItem.
     *
     * @param shopItem the item we want to buy
     */
    void buyItem(ShopItem shopItem);

    /**
     * Given the RESOURCE_TYPE returns all the items associated with that resource.
     *
     * @param type of the resource
     *
     * @return a list of ShopItem
     */
    List<ShopItem> findItemsByResourceType(ResourceType type);

    /**
     * Gets the stop items.
     *
     * @return the set of shop items
     */
    List<ShopItem> getShopItems();
}
