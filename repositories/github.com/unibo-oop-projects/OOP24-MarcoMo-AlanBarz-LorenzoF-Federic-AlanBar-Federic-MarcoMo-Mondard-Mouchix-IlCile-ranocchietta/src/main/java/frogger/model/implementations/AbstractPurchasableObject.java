package frogger.model.implementations;

import frogger.model.interfaces.PurchasableObject;

/**
 * Abstract base class for purchasable objects in the shop.
 * Implements common properties and methods for all purchasable objects.
 */
public abstract class AbstractPurchasableObject implements PurchasableObject {
    /** The price of the purchasable object. */
    private final int prize;
    /** The image associated with the purchasable object. */
    private final String img;
    /** Whether the object has been bought/activated. */
    private boolean isBought;

    /**
     * Constructs a new AbstractPurchasableObject.
     *
     * @param prize the price of the object
     * @param img the image representing the object
     * @param isBought true if the object is already bought, false otherwise
     */
    public AbstractPurchasableObject(final int prize, final String img, final boolean isBought) {
        this.prize = prize;
        this.img = img;
        this.isBought = isBought;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrize() {
        return this.prize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return this.img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        return this.isBought;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvailable(final boolean b) {
        this.isBought = b;
    }

    /**
     * Returns a string representation of this purchasable object.
     *
     * @return a string containing the class, prize, image, and availability
     */
    @Override 
    public String toString() {
        return this.getClass() + " " + this.prize + " " + this.img + " " + this.isBought;
    }
}
