package it.unibo.ninjafrog.game.utilities;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals and toString.
 * 
 * @param <X> First type parameter.
 * @param <Y> Second type parameter.
 */
public final class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Public constructor of a generic Pair<X,Y>.
     * 
     * @param x first component of the pair.
     * @param y second component of the pair.
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * First parameter getter.
     * 
     * @return the first component of the pair.
     */
    public X getX() {
        return x;
    }

    /**
     * Second parameter getter.
     * 
     * @return the second component of the pair.
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

}
