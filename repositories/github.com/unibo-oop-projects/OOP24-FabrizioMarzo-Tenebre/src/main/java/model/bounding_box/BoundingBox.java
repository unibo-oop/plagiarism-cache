package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents a bounding box used for collision detection in the model.
 * <p>
 * A bounding box is defined by its upper-left and bottom-right corners.
 */
public interface BoundingBox {

    /**
     * Checks whether this bounding box is colliding with another bounding box.
     *
     * @param otherBBox the other bounding box to test collision against
     * @return {@code true} if the bounding boxes collide, {@code false} otherwise
     */
    boolean isColliding(final BoundingBox otherBBox);

    /**
     * Returns the upper-left corner coordinates of the bounding box.
     *
     * @return a {@link Pair} representing the (x, y) of the upper-left corner
     */
    Pair<Double, Double> getULcorner();

    /**
     * Returns the bottom-right corner coordinates of the bounding box.
     *
     * @return a {@link Pair} representing the (x, y) of the bottom-right corner
     */
    Pair<Double, Double> getBRcorner();

    /**
     * Updates the bounding box position based on a new reference position.
     *
     * @param newPos the new position to update the bounding box to
     */
    void updateBBox(final Pair<Double, Double> newPos);
}
