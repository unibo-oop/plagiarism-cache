package zombietsunami;
/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 * 
 * @param <X> is the type of the first element.
 * @param <Y> is the type of the secoond element.
 */
public final class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Contructor of the class, creates a new Pair and sets the given parameters.
     * 
     * @param x is the first element.
     * @param y is the second element.
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return the first element of the Pair.
     */
    public X getX() {
        return x;
    }

    /**
     * @return the second element of the Pair.
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

}
