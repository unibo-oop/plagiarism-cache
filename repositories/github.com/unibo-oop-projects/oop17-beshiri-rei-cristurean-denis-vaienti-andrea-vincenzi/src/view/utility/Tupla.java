package view.utility;

/**
 * Represents a tuple as one element, used to set the resolution.
 *
 * @param <X>
 *            The type of the first element.
 * @param <Y>
 *            The type of the second element.
 */
public class Tupla<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructor of the class.
     * 
     * @param x
     *            The Width element.
     * @param y
     *            The Height element.
     */
    public Tupla(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the Width.
     * 
     * @return The Width element.
     */
    public X getX() {
        return x;
    }

    /**
     * Getter for the Height.
     * 
     * @return The Height element.
     */
    public Y getY() {
        return y;
    }
}
