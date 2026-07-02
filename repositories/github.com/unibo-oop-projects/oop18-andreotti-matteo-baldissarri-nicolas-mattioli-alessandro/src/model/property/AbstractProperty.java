package model.property;

/**
 * This class provides a skeletal implementation of the {@link Property}
 * interface to minimize the effort required to implement this interface.
 */
public abstract class AbstractProperty implements Property {

    private int value;

    /**
     * @param value The initial value of the property
     */
    public AbstractProperty(final int value) {
        this.value = value;
    }

    /**
     * @return The current value of the property
     */
    @Override
    public int getValue() {
        return this.value;
    }

    /**
     * Increment the value of the property.
     */
    @Override
    public void increment() {
        if (!this.isMaximum()) {
            this.value++;
        }
    }

    /**
     * Decrement the value of the property.
     */
    @Override
    public void decrement() {
        if (!this.isConsummate()) {
            this.value--;
        }
    }

    @Override
    public abstract boolean isMaximum();

    @Override
    public abstract boolean isConsummate();

    /**
     * Returns a string representation of the Property.
     * 
     * @return A string representation of the Property
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.value;
    }

}
