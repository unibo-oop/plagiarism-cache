package utilities;

/**
 * 
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * 
     * @param first 
     * @param second 
     */
    public Pair(final X first, final Y second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 
     * @return X
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * 
     * @return Y
     */
    public Y getSecond() {
        return this.second;
    }

    /**
     * 
     * @return String
     */
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }
}
