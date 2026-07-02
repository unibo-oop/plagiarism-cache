package it.unibo.model.shop.api;

import java.util.List;
import java.util.Optional;

/**
 * Factory for creating all items available in the shop.
 */
public interface ShopItemFactory {

    /**
     * @return a list containing all the skins.
     */
    List<ShopItem> getSkins();

    /**
     * @return a list containing all the temporary power-ups.
     */
    List<ShopItem> getPowerUpsTemporary();

    /**
     * @return a list containing all the permanent power-ups.
     */
    List<ShopItem> getPowerUpsPermanent();

    /**
     * @return a list containing every single item in the shop.
     */
    List<ShopItem> getAllItems();

    /**
     * Return a specific item by its unique ID.
     * 
     * @param itemId the ID of the item
     * @return an Optional containing the corresponding ShopItem if it exists
     */
    Optional<ShopItem> getItemById(String itemId);
}
