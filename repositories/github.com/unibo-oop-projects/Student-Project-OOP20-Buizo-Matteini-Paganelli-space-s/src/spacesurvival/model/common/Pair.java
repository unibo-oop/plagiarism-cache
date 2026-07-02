package spacesurvival.model.common;
/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented. 
 */

public class Pair<X, Y> {

    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Return the X type of the pair.
     * 
     * @return x the element of the pair of type X
     */
    public X getX() {
        return this.x;
    }

    /**
     * Return the Y type of the pair.
     * 
     * @return y the element of the pair of type Y
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Return an hash code of the current pair.
     * 
     * @return the hash integer
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.x == null) ? 0 : this.x.hashCode());
        result = prime * result + ((this.y == null) ? 0 : this.y.hashCode());
        return result;
        }

    /**
     * Method equal for pair type.
     * 
     * @param obj the object to compare
     * @return true if equals
     */
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
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            return other.y == null;
        } else {
            return y.equals(other.y);
        }
    }

    /**
     * Return a describing string of the pair.
     * 
     * @return the representing string
     */
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}

