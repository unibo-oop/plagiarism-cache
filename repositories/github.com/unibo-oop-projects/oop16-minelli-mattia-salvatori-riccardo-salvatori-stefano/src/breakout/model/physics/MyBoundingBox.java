package breakout.model.physics;
import javafx.geometry.Point2D;

/**
 * A class that defines an AABB bounding box.<br/>
 * adding some methods to {@link javafx.geometry.BoundingBox}
 *
 */
public class MyBoundingBox extends javafx.geometry.BoundingBox {

    /* The vertices of the bounding box */
    private final Point2D upLeft;
    private final Point2D upRight;
    private final Point2D bottomRight;
    private final Point2D bottomLeft;

    /**
     * {@link javafx.geometry.BoundingBox}.
     * 
     * @param minX
     *          X coordinate of the upper left point
     * @param minY
     *          Y coordinate of the upper left point
     * @param width
     *          width of the rectangle
     * @param height
     *          height of the rectangle
     */
    public MyBoundingBox(final double minX, final double minY, final double width, final double height) {
        super(minX, minY, width, height);
        this.upLeft = new Point2D(this.getMinX(), this.getMinY());
        this.upRight = new Point2D(upLeft.getX() + this.getWidth(), upLeft.getY());
        this.bottomRight = new Point2D(this.getMaxX(), this.getMaxY());
        this.bottomLeft = new Point2D(this.getMinX(), this.getMaxY());
    }

    /**
     * @param collided
     *            the colliding object
     * @return the area of the intersection between this bounding box and the given boundingbox.
     */
    public double intersectingArea(final MyBoundingBox collided) {
        return this.overlapX(collided) * this.overlapY(collided);
    }

    /**
     * @return the area of the bounding box
     */
    public double area() {
        return this.getWidth() * this.getHeight();
    }

    /**
     * @param collided
     *            the object colliding with this bounding box
     * @return a double rappresenting the width of the rectangle formed by the
     *         intersections of the two colliding bounding box
     */
    public double overlapX(final MyBoundingBox collided) {
        if (collided.contains(upRight) || collided.contains(bottomRight)) {
            return Math.abs(upRight.getX() - collided.getMinX());
        } else {
            return Math.abs(collided.getMaxX() - bottomLeft.getX());
        }
    }

    /**
     * @param collided
     *            the object colliding with this bounding box
     * @return a double rappresenting the height of the rectangle formed by the
     *         intersections of the two colliding bounding box
     */
    public double overlapY(final MyBoundingBox collided) {
        if (collided.contains(upRight) || collided.contains(upLeft)) {
            return Math.abs(upRight.getY() - collided.getMaxY());
        } else {
            return Math.abs(bottomRight.getY() - collided.getMinY());
        }
    }

}
