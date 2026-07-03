package model.implementations;

/**
 * Implements a generic pair.
 * 
 * @param <X>    the type-variable of the first element of the pair
 * @param <Y>    the type-variable of the second element of the pair
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Creates a new pair.
     * 
     * @param frst    the first element of the pair
     * @param scnd    the second element of the pair
     */
    public Pair(final X frst, final Y scnd) {
        this.first = frst;
        this.second = scnd;
    }

    /**
     * 
     * @return the first element of the pair
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * 
     * @return the second element of the pair
     */
    public Y getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }
}
