package model.pkglevels;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString.
 * well implemented.
 * 
 * @param <X>
 *            generic type X
 * @param <Y>
 *            generic type Y
 */

public class Pair<X, Y> implements Comparable<X> {

    private final X x;
    private final Y y;

    /**
     * Class constructor.
     * 
     * @param x
     *            value
     * @param y
     *            value
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value.
     * 
     * @return value
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the y value.
     * 
     * @return value
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

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int compareTo(final X o) {

        return 0;
    }

}
