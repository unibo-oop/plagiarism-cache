package frogger.model.interfaces;

import frogger.model.implementations.Skin;

/**
 * Factory interface for creating purchasable objects for the shop.
 */
public interface PurchasableObjectFactory {

    /**
     * Creates a new Skin object with the specified parameters.
     *
     * @param prize the price of the skin
     * @param img the image representing the skin
     * @param isBought true if the skin is already bought, false otherwise
     * @return a new Skin instance
     */
    Skin createSkin(int prize, String img, boolean isBought);
}
