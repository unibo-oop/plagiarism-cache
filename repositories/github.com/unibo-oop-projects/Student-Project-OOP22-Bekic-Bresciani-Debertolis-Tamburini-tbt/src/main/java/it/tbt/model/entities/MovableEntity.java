package it.tbt.model.entities;

/**
 * Generic entity with a variable position in space.
 */
public interface MovableEntity extends SpatialEntity {

    /**
     * Set X coordinate.
     * @param x
     */
    void setX(int x);

    /**
     * Set Y coordinate.
     * @param y
     */
    void setY(int y);
}
