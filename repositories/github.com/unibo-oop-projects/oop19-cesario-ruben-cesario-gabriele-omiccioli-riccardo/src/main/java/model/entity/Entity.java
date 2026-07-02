package model.entity;

import utilities.math.Point2D;

/**
 *  Models a visible entity, determined by its identifier, which describes
 *  the category it belongs to, and its position.
 */
public interface Entity {

    /**
     * Returns the identifier associated with this entity.
     * @return the identifier associated with this entity.
     */
    EntityID getID();

    /**
     * Changes the position of this entity instantaneously.
     * @param newPosition : the new position of this entity.
     */
    void resetPosition(Point2D newPosition);

    /**
     * Returns the position of this entity.
     * @return the position of this entity.
     */
    Point2D getPosition();
}
