package utilities;

/**
 * A simple implementation of a pair class.
 *
 */

public class Pair<X, Y> {
    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    public final X getX() {
        return this.x;
    }

    public final Y getY() {
        return this.y;
    }
    @Override
    public String toString() {
            return "<" + this.x + "," + this.y + ">";
    }
}
