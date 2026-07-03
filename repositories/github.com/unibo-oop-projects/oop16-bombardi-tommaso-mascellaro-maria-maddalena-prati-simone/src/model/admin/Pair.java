package model.admin;

import java.io.Serializable;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString. 
 *
 * @param <X>
 *          first element of pair
 * @param <Y>
 *          second element of pair
 */
public class Pair<X, Y> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int PRIME = 31;
    private final X x;
    private final Y y;

    /**
     * Pair constructor.
     * 
     * @param el1
     *          element x of pair
     * @param el2
     *          element y of pair
     */
    public Pair(final X el1, final Y el2) {
        super();
        this.x = el1;
        this.y = el2;
    }
 
    /**
     * Get X.
     * 
     * @return the element x of pair
     */
    public X getX() {
        return x;
    }

    /**
     * Get Y.
     * 
     * @return the element y of pair
     */
    public Y getY() {
        return y;
    }

    /**
     * Hash code.
     * 
     * @return an integer which is the hashcode of the pair
     */
    public int hashCode() {
        int result = 1;
        result = PRIME * result + ((x == null) ? 0 : x.hashCode());
        result = PRIME * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * Equals.
     * 
     * @param obj
     *              object which will be compared to the pair
     * @return true if the pair and the object are equals, false otherwise
     */
    @SuppressWarnings("rawtypes")
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
     * To string.
     * 
     * @return a string that describes the pair
     */
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}
