package it.unibo.jurassiko.common;

/**
 * A simple record class representing an immutable pair of two objects.
 * 
 * @param <X> The type of the first generic element
 * @param <Y> The type of the second generic element
 * @param x   The value of the first element
 * @param y   The value of the second element
 */
public record Pair<X, Y>(X x, Y y) {

    /**
     * Constructor used to crete a Copy of the Pair.
     * 
     * @param pair input Pair
     */
    public Pair(final Pair<X, Y> pair) {
        this(pair.x(), pair.y());
    }

}
