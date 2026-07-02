package util;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 *
 * @param <X> First parameter
 * @param <Y> Second parameter
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    public final X getX() {
        return x;
    }

    public final Y getY() {
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
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            return other.y == null;
        } else {
            return y.equals(other.y);
        }
    }

    @Override
    public final String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
