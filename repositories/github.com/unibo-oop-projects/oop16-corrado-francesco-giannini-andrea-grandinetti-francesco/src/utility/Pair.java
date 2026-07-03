package utility;

/**
 * A class used to have in a same place two data related together.
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * .
     * @param x the first element
     * @param y the second element
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get the first element.
     * @return the x element
     */
    public X getX() {
        return this.x;
    }

    /**
     * Method to get the second element.
     * @return the y element
     */
    public Y getY() {
        return this.y;
    }

}
