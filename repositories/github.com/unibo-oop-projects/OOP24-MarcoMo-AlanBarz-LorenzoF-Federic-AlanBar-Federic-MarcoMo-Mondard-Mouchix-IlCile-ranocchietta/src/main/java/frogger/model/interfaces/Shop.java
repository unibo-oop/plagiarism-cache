package frogger.model.interfaces;

import java.util.List;

/**
 * Interface representing a shop that manages purchasable objects in the game.
 * Provides methods for retrieving, initializing, and updating the list of purchasable objects.
 */
public interface Shop {

    /**
     * Returns a copy of the list of purchasable objects available in the shop.
     *
     * @return a new list containing the purchasable objects
     */
    List<PurchasableObject> getPurchasableObjects();

    /**
     * Initializes the shop by loading purchasable objects from the save file if it exists,
     * or from the default resource file otherwise.
     * Populates the internal list of purchasable objects.
     */
    void init();
}
