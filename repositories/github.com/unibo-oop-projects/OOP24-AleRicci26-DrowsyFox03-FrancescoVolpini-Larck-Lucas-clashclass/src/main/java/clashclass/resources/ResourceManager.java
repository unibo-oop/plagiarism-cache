package clashclass.resources;

/**
 * Represents the in-game resources.
 */
public interface ResourceManager {
    /**
     * Increase the amount of resource by a given value.
     *
     * @param value the amount of resource to increase
     */
    void increase(double value);

    /**
     * Decrease the amount of resource by a given value.
     *
     * @param value the amount of resource to decrease
     */
    void decrease(double value);

    /**
     * Returns the current value of the resource.
     *
     * @return the resource value as a double
     */
    double getCurrentValue();

    /**
     * Returns the max value of the resource.
     *
     * @return the max value of the resource
     */
    double getMaxValue();
}
