package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObjectFactory;

/**
 * Implementation of the PurchasableObjectFactory interface.
 * Provides methods to create purchasable objects for the shop.
 */
public class PurchasableObjectFactoryImpl implements PurchasableObjectFactory {

    /**
     * {@inheritDoc}
     * Creates a new Skin object with the specified parameters.
     *
     * @param prize the price of the skin
     * @param img the image representing the skin
     * @param isBought true if the skin is already bought, false otherwise
     * @return a new Skin instance
     */
    @Override
    public Skin createSkin(final int prize, final String img, final boolean isBought) {
        return new Skin(prize, img, isBought);
    }
}
