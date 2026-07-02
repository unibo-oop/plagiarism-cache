package it.unibo.bmbman.model.utilities;

/**
 * A generic class to model a pair of values.
 *
 * @param <X> the first element
 * @param <Y> the second element
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;
    /**
     * Construct the pair.
     * @param x the first element
     * @param y the second element
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }
    /**
     * Used to get the first element.
     * @return x component of the pair
     */

    public X getX() {
        return x;
    }
    /**
     * Used to get the second element.
     * @return y component of the pair
     */
    public Y getY() {
        return y;
    }
    /**
     * Used to generate the hash code.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }
    /**
     * This method established if obj is equals to the object.
     */
    @SuppressWarnings("rawtypes")
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
        final Pair other = (Pair) obj;
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

    /**
     * Used to represented the pair as a string.
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }



}
