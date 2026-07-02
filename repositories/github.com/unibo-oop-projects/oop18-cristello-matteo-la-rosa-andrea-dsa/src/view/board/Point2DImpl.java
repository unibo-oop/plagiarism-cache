package view.board;
/**
 * This is the implementation of Point2D interface.
 */
public class Point2DImpl implements Point2D {

    private final int x;
    private final int y;
    /**
     * Class constructor.
     * @param x The X coordinate;
     * @param y The Y coordinate;
     */
    public Point2DImpl(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final int getX() {
        return this.x;
    }

    @Override
    public final int getY() {
        return this.y;
    }

}
