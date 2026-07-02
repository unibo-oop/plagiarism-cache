package utilityclasses;
/**
 * generic class of Pair. 
 * @param <X>
 * @param <Y>
 */
public class Pair<X,Y> {

    private final X x;
    private final Y y;

    /**
     * constructor of the Pair.
     * @param x the X coord
     * @param y the Y coord
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @return the X coord of the pair
     */
    public X getX() {
        return x;
    }
    /**
     * 
     * @return the Y coord of the pair
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
     * prints return the toString.
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
