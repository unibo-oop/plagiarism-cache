package it.unibo.oop18.cfc.Util;

/**
 * Utility class that models a couple of generic values.
 *
 * @param <X> first value
 * @param <Y> second value
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Creates {@code Pair}.
     *
     * @param first value
     * @param second value
     */
    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets first value.
     *
     * @return first value
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Gets second value.
     *
     * @return second value
     */
    public Y getSecond() {
        return this.second;
    }

}
