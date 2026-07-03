package model.manager;

import java.util.Objects;

import javafx.scene.shape.Shape;

/**
 * 
 * Utility Class to check collisions between Entities.
 *
 */
public final class Collisions {

    private Collisions() {

    }

    /**
     * Check the intersection between two shapes.
     * 
     * @param shape
     *            the first shape.
     * @param shape2
     *            the second shape.
     * @return true if there is an intersection between the two entities.
     */
    public static boolean checkShapesIntersection(final Shape shape, final Shape shape2) {
        Objects.requireNonNull(shape);
        Objects.requireNonNull(shape2);
        return Shape.intersect(shape, shape2).getBoundsInLocal().getWidth() != -1;
    }

}
