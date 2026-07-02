package it.unibo.model.interfaces;

import it.unibo.model.Point2DI;

/**
 * An interface representing a rectangle.
 * Defines a method to check whether a 2D point is inside the rectangle.
 */
public interface RectangleInterface {

    /**
     * Determines whether the given point is inside the rectangle.
     * 
     * @param pos The {@link Point2DI} representing the point to check.
     * 
     * @return {@code true} if the area contains the given point, {@code false}
     *         otherwise.
     */
    boolean isInside(Point2DI pos);
}
