package pair;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that create an object composed by two elements.
 * The type of the pair object is specified by the types of the elements it is made of.
 *
 * @param <X> : first element of the object pair.
 * @param <Y> : second element of the object pair.
 */
public class Pair<X, Y> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2128312219893351425L;

    private final X x;
    private final Y y;

    /**
     * @param x 
     * @param y 
     */
    @JsonCreator
    public Pair(@JsonProperty("x") final X x, @JsonProperty("y") final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return a generic that contains the first element of the pair.
     */
    public X getX() {
        return x;
    }

    /**
     * @return a generic that contains the second element of the pair.
     */
    public Y getY() {
        return y;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
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

    @Override
    public final String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

}
