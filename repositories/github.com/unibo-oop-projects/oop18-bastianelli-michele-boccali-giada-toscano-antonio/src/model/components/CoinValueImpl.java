package model.components;

/**
 * Implementation of money component.
 */
public class CoinValueImpl implements Component, CoinValue {

    private int value;

    /**
     * Create a money component.
     * 
     * @param value the value be added to the score when the entity has been killed
     */
    public CoinValueImpl(final int value) {
        this.value = value;
    }

    @Override
    public final int getValue() {
        return value;
    }

    @Override
    public final void setValue(final int value) {
        this.value = value;
    }

}
