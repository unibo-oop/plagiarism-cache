package utils;

/**
 * Utility class for pairs of elements.
 * 
 * @param <X> The first element
 * @param <Y> The second element
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * @param x The first element
     * @param y The second element
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The first element
     */
    public X getX() {
        return this.x;
    }

    /**
     * @return The second element
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Define the hashcode of this Pair.
     * 
     * @return The hashcode.
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
     * Determines of two pairs are equal.
     * 
     * @param obj A possible pair to compare
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
        Pair other = (Pair) obj;
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
     * Returns a string representation of the Pair.
     * 
     * @return A string representation of the Pair.
     */
    @Override
    public String toString() {
        return "Pair [x=" + this.x + ", y=" + this.y + "]";
    }

}
