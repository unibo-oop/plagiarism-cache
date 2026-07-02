package model.property;

/**
 * Models the property Life of the Stuntman.
 */
public final class Life extends AbstractProperty {

    private static final int MAX_VALUE = 3;

    /**
     * Initializes the life's value, which is initially at its maximum.
     */
    public Life() {
        super(MAX_VALUE);
    }

    @Override
    public boolean isMaximum() {
        return this.getValue() >= MAX_VALUE;
    }

    @Override
    public boolean isConsummate() {
        return this.getValue() <= 0;
    }

}
