package utilities;

/**
 * Utility class that keeps two values, even of different types.
 *
 * @param <X> Type of the first element.
 * @param <Y> Type of the second element.
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructor for pair.
     * @param x The first element of the pair.
     * @param y The second element of the pair.
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Only way to get the first element.
     * @return x The first element.
     */
    public X getX() {
        return x;
    }

    /**
     * Only way to get the second element.
     * @return x The second element.
     */
    public Y getY() {
        return y;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<X, Y> other = (Pair<X, Y>) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

}
