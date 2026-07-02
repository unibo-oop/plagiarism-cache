package it.tbt.model.entities;

/**
 * Generic entity with a position in space.
 */
public interface SpatialEntity extends Entity {

    /**
     * Get X coordinate.
     * @return X coordinate
     */
    int getX();

    /**
     * Get Y coordinate.
     * @return Y coordinate
     */
    int getY();

    /**
     * Get height.
     * @return height
     */
    int getHeight();

    /**
     * Get width.
     * @return width
     */
    int getWidth();
}
