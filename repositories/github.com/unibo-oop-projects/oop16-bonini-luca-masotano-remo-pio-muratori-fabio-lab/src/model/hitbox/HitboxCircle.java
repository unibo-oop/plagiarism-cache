package model.hitbox;

/**
 * 
 * The HitboxCircle class defines a circle with the specified size and location.
 * This shape is used for collisions between entities.
 *
 */
public class HitboxCircle extends HitboxImpl {

    /**
     * A unique serial version identifier.
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 1059092100874741852L;
    private final double radius;

    /**
     * Constructs a new instance of the class HitboxCircle.
     * 
     * @param x
     *            The x coordinate of the center of the circle.
     * @param y
     *            The y coordinate of the center of the circle.
     * @param radius
     *            The radius of this circle.
     */
    public HitboxCircle(final double x, final double y, final double radius) {
        super(x, y);
        this.radius = radius;
    }

    /**
     * Get the value of the radius of this circle.
     * 
     * @return The radius.
     */
    public double getRadius() {
        return radius;
    }
}
