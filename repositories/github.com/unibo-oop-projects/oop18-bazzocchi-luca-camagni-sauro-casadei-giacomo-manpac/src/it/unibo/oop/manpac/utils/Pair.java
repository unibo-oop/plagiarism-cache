package it.unibo.oop.manpac.utils;

/**
 * A pair of two generic from which the value can be taken.
 *
 * @param <X> first parameter
 * @param <Y> second parameter
 */
public interface Pair<X, Y> {

    /**
     * Get the first parameter.
     * 
     * @return The first parameter
     */
    X getFirst();

    /**
     * Get the second parameter.
     * 
     * @return The second parameter
     */
    Y getSecond();
}
