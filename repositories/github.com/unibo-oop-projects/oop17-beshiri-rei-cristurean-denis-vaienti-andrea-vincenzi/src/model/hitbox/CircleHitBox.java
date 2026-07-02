package model.hitbox;

/**
 * Circle HitBox.
 *
 */
public class CircleHitBox extends AbstractHitBox {

    private final double radius;

    /**
     * Constructor for this class.
     * @param x
     *            X center of the circle.
     * @param y
     *            Y center of the circle.
     * @param r
     *            Radius.
     */
    public CircleHitBox(final double x, final double y, final double r) {
        super(x, y);
        radius = r;
    }

    /**
     * Getter for radius of entity.
     * @return Return the radius of the circle.
     */
    public double getRadius() {
        return radius;
    }
}
