package util;

/**
 * Provides a representation of a pair of two variables as one element.
 * 
 * @param <X>
 *            the X type of the first element
 * @param <Y>
 *            the Y type of the second element
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructor of class Pair<X, Y>.
     * 
     * @param x
     *            the X type element
     * @param y
     *            the Y type element
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of the X type element.
     * 
     * @return the X type element
     */
    public X getX() {
        return x;
    }

    /**
     * Getter of the Y type element.
     * 
     * @return the Y type element
     */
    public Y getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
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
