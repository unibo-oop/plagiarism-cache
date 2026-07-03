package utilities;

/**
 * A pair of objects.
 * 
 * @param <X>
 *            the type of the first object
 * @param <Y>
 *            the type of the first object
 */
public class Pair<X, Y> {
    private final X x;
    private final Y y;

    /**
     * Creates a pair instance.
     * 
     * @param x
     *            the first object of the pair
     * @param y
     *            the second object of the pair
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first object of the pair.
     * 
     * @return the first object of the pair
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the second object of the pair.
     * 
     * @return the second object of the pair
     */
    public Y getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}