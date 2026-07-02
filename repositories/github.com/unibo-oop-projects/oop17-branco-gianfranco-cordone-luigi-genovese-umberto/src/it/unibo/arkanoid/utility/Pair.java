package it.unibo.arkanoid.utility;

/**
 * 
 * Class that contains two generic Subjects.
 * 
 * @param <T>
 *            generic param.
 */
public class Pair<T> {

    private final T first;
    private final T second;

    /**
     * 
     * @param first
     *      first element
     * @param second
     *      second element
     */
    public Pair(final T first, final T second) {
        this.first = first;
        this.second = second;

    }

    /**
     * 
     * @return first
     */
    public T getFirst() {
        return first;
    }

    /**
     * 
     * @return second
     */
    public T getSecond() {
        return second;
    }

}
