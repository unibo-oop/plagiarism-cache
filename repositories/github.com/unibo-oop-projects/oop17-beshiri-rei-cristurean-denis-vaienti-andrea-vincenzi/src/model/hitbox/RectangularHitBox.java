package model.hitbox;

/**
 * Rectangular HitBox.
 *
 */
public class RectangularHitBox extends AbstractHitBox {

    private final double height;
    private final double width;

    /**
     * Constructor for this class.
     * 
     * @param x
     *            Initial center X.
     * @param y
     *            Initial center Y.
     * @param h
     *            Initial height for rect.
     * @param w
     *            Initial width for rect.
     */
    public RectangularHitBox(final double x, final double y, final double h, final double w) {
        super(x, y);
        height = h;
        width = w;
    }

    /**
     * Getter for rectangular height.
     * 
     * @return Return height of rect.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter for rectangular width.
     * 
     * @return Return weight of rect.
     */
    public double getWidth() {
        return width;
    }
}
