package model.components;

/**
 * Money interface to create a money component.
 */
public interface CoinValue {

    /**
     * Get the money value of the component.
     * 
     * @return the money value
     */
    int getValue();

    /**
     * Set how much the contained money must value. This value will be added to the
     * score when the entity has been killed.
     * 
     * @param value the money value
     */
    void setValue(int value);
}
