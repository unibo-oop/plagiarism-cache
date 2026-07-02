package other;

/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 * Written By Viroli
 */

public class Pair<X, Y> implements PairInterface {

    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return X
     * 
     * getter X
     * 
     */
    public final X getX() {
        return x;
    }

    /**
     * @return Y
     * getter Y
     */
    public final Y getY() {
        return y;
    }

    /**
     * @return hashCode
     * 
     * method to get an hashcode to generate a unique code
     *
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
     * @return true if obj is equals with this object
     *
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
     * @return string
     * toString method
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}
