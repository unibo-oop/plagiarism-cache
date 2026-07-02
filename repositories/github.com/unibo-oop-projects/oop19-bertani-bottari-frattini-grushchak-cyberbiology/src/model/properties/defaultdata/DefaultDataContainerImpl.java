package model.properties.defaultdata;


import model.properties.utilities.NumbersComparator;

/**
 * 
 * A class that contains the possible range and a default value of different data of application.
 *
 */
public class DefaultDataContainerImpl<T extends Number> implements DefaultDataContainer<T>{

    private final T min;
    private final T max;
    private final T defaultValue;

    /**
     * @param min          a possible minimum of value.
     * @param max          a possible maximum of value.
     * @param defaultValue a default value.
     * @throws IllegalArgumentException if min is greater than max, or defaultValue
     *                                  is less than min, or defaultValue is greater
     *                                  than max.
     */
    DefaultDataContainerImpl(final T min, final T max, final T defaultValue) {
        checkValues(min, max, defaultValue);
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
    }

    @Override
    public final T getMinimumValue() {
        return this.min;
    }

    @Override
    public final T getMaximumValue() {
        return this.max;
    }

    @Override
    public final T getDafaultValue() {
        return this.defaultValue;
    }
    
    private void checkValues(final T min, final T max, final T defaultValue) {
        if(NumbersComparator.isBiggerThan(min, max)
                || NumbersComparator.isBiggerThan(defaultValue, max)
                || NumbersComparator.isBiggerThan(min, defaultValue)) {
            throw new IllegalArgumentException("Illegal value of min, max or defaultValue.");
        }
    }
}
