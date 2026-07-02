package model.utility;

/**
 * Simple Pair to memorize two object.
 *
 * @param <X>
 *            First type of object
 * @param <Y>
 *            Second type of object
 */
public class Pair<X, Y> {
    private X x;
    private Y y;

    /**
     * Simple constructor.
     * 
     * @param x
     *            first object
     * @param y
     *            second object
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter.
     * 
     * @return first object
     */
    public X getFirst() {
        return this.x;
    }

    /**
     * Getter.
     * 
     * @return the second object
     */
    public Y getSecond() {
        return this.y;
    }

    /**
     * Setter.
     * 
     * @param x
     *            first object to set
     */
    public void setFirst(final X x) {
        this.x = x;
    }

    /**
     * Setter.
     * 
     * @param y
     *            second object to set
     */
    public void setSecond(final Y y) {
        this.y = y;
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
        if (!(obj instanceof Pair)) {
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
