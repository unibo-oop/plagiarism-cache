package element;

public interface Point2D {

    /**
     * @return the x coordinate of the point
     */
    double getX();

    /**
     * @return the y coordinate of the point
     */

    double getY();

    /**
     * @param v the v of the direction to sum
     * @return a new point that is the current point summed to the vector v
     */

    Point2D sum(Vector2D v);

    /**
     * @param p the other point
     * @return a new vector which is the subtraction of the two points' coordinates (x, y)
     */

    Vector2D subtraction(Point2D p);

    /**
     * @param p the other point
     * @return the distance between the two points
     */

    double distance(Point2D p);


}
