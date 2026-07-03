package model.hitbox;

/**
 * 
 * The HitboxRectangle class defines a rectangle with the specified size and
 * location. This shape is used for collisions between entities.
 *
 */
public class HitboxRectangle extends HitboxImpl {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 6356810226686006288L;
    private final double width;
    private final double height;

    /**
     * Constructs a new instance of the class HitboxRectangle.
     * 
     * @param x
     *            The x coordinate of the upper-left corner of the rectangle.
     * @param y
     *            The y coordinate of the upper-left corner of the rectangle.
     * @param width
     *            The width of the rectangle.
     * @param height
     *            The height of the rectangle.
     */
    public HitboxRectangle(final double x, final double y, final double width, final double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Get the value of the width.
     * 
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the value of the height.
     * 
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return height;
    }
}
