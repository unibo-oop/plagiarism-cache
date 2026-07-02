package frogger.model.implementations;

/**
 * Represents a skin that can be purchased and equipped by the player.
 * Extends {@link AbstractPurchasableObject} to provide skin-specific functionality.
 */
public class Skin extends AbstractPurchasableObject {

    /**
     * Constructs a new Skin object with the specified parameters.
     *
     * @param prize the price of the skin
     * @param img the image representing the skin
     * @param isBought true if the skin is already bought, false otherwise
     */
    public Skin(final int prize, final String img, final boolean isBought) {
        super(prize, img, isBought);
    }
}
