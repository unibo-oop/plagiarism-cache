package model.statistic;

/**
 * Represents a generic single statistic, given some input data.
 * @param <T> generic type 
 */
public interface Statistic<T extends Comparable<T>> extends Comparable<Statistic<T>> {
    /**
     * @return the name of the statistic
     */
    String getName();
    /**
     * @return the computed value
     */
    T getValue();
}
