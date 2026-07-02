package it.unibo.isaccoop.model.boundingbox;

import it.unibo.isaccoop.model.common.Point2D;

/**
 * Interface that model the bounding box.
 */
public interface BoundingBox {

    /**
     * Check for collisions of two bounding box.
     * @param center of first bounding box
     * @param center1 of the second bounding box
     * @param circleBox of the second bounding box
     * @return true if a collision occours
     */
    boolean isCollidingWithCricle(Point2D center, Point2D center1, CircleBoundingBox circleBox);

    /**
     * Check for collisions of a bounding box with the perimeter of a rectangle bounding box.
     * (We suppose that the first bounding box is inside the second one).
     * @param center of first bounding box
     * @param rectangleBox
     * @return true if a collision occours
     */
    boolean isCollidingWithRecPerimeter(Point2D center, RectBoundingBox rectangleBox);

}
