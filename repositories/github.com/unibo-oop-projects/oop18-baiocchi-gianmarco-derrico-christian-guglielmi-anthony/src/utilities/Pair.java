package utilities;

/**
 * Utility class that models a couple of generic values.
 * @param <X> : First value.
 * @param <Y> : Second value.
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Builds a pair.
     * @param first : first value.
     * @param second : second value.
     */
    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets first value.
     * @return first value.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Gets second value.
     * @return second value.
     */
    public Y getSecond() {
        return this.second;
    }

}
