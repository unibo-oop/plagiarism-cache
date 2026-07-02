package utilities.math;

/**
 *  Models a point in a 2-dimensional space.
 */
public interface Point2D {

    /**
     * Returns the X value of this point.
     * @return Returns the X value of this point
     */
    double getX();

    /**
     * Returns the Y value of this point.
     * @return Returns the Y value of this point
     */
    double getY();

    /**
     * Sets the X value of this point.
     * @param x The value to be set
     */
    void setX(double x);

    /**
     * Sets the Y value of this point.
     * @param y The value to be set
     */
    void setY(double y);

    /**
     * Translates the point by the specified translation.
     * @param translation The coordinates to be added to the point
     * @return A new point, translation of this point
     */
    Point2D translate(Point2D translation);

    /**
     * Calculate and returns the distance between the point and the point passed to the method.
     * @param point Point
     * @return The distance between the point and the point passed to the method
     */
    double distance(Point2D point);

}
