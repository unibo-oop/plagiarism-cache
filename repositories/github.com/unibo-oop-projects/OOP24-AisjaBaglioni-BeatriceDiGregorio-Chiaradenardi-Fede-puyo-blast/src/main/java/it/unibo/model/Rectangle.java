package it.unibo.model;

import it.unibo.model.interfaces.RectangleInterface;

/**
 * A utility class representing the area of a rectangle ,
 * given the upper-left and the lower-right corners.
 * It provides a method for checking whether a 2D point is inside the area.
 */
public class Rectangle implements RectangleInterface {
    /**
     * The upper left corner in {@link Point2DI}
     */
    public Point2DI upleft;
    /**
     * The lower right corner in {@link Point2DI}
     */
    public Point2DI lowright;

    /**
     * Constructs a new rectangle based on two points
     * 
     * @param upleft   The upper left corner
     * @param lowright The lower right corner
     */
    public Rectangle(Point2DI upleft, Point2DI lowright) {
        this.upleft = upleft;
        this.lowright = lowright;
    }

    /**
     * Checks if the given point is inside the rectangle.
     * 
     * @param pos The Point2DI whose position needs to be checked
     * 
     * @return {@code true} if the area contains the point, {@code false} otherwise.
     */
    @Override
    public boolean isInside(Point2DI pos) {
        return (pos.x() >= upleft.x() && pos.x() <= this.lowright.x() && pos.y() <= this.lowright.y()
                && pos.y() >= this.upleft.y());
    }
}
