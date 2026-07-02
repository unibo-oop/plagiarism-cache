package model.property;

/**
 * Models a property of a Character.
 */
public interface Property {

    /**
     * @return The current value of the property
     */
    int getValue();

    /**
     * Increment the value of the property.
     */
    void increment();

    /**
     * Decrement the value of the property.
     */
    void decrement();

    /**
     * @return If the property is at its maximum
     */
    boolean isMaximum();

    /**
     * @return If the property is consumed
     */
    boolean isConsummate();

}
