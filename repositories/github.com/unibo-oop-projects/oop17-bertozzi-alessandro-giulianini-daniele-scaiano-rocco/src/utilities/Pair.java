package utilities;

/**
 * @param <X> the type of the first element of this pair
 * @param <Y> the type of the first element of this pair
 */
public final class Pair<X, Y> {

    private X x;
    private Y y;

    /**
     * @param x first element of the pair
     * @param y second element of the pair
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return the first element of the pair
     */
    public X getX() {
        return x;
    }

    /**
     * @return the second element of the pair
     */
    public Y getY() {
        return y;
    }

    /**
     * @param x the new first element
     */
    public void setX(final X x) {
        this.x = x;
    }

    /**
     * @param y the new second element
     */
    public void setY(final Y y) {
        this.y = y;
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
