package frogger.model.interfaces;

/**
 * Represents an object that can be purchased in the shop.
 * Provides methods to access its price, image, and availability.
 */
public interface PurchasableObject {

    /**
     * Returns the price of the purchasable object.
     *
     * @return the price of the object
     */
    int getPrize();

    /**
     * Returns the image associated with the purchasable object.
     *
     * @return the image path or identifier
     */
    String getImage();

    /**
     * Returns whether the object is available (bought/unlocked).
     *
     * @return true if available, false otherwise
     */
    boolean isAvailable();

    /**
     * Sets the availability status of the object.
     *
     * @param b true if the object is available, false otherwise
     */
    void setAvailable(boolean b);
}
