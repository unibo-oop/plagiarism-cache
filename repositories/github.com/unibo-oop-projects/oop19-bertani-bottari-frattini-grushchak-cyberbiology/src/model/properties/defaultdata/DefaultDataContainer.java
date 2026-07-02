package model.properties.defaultdata;

/**
 * 
 * A interface for a container for different possible values of data.
 *
 */
public interface DefaultDataContainer<T extends Number> {
    /**
     * @return a minimum value of enum.
     */
    T getMinimumValue();

    /**
     * @return a maximum value of enum.
     */
    T getMaximumValue();

    /**
     * @return a default value of enum.
     */
    T getDafaultValue();
}
