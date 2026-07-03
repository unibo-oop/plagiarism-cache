package model.entities;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

/**
 * An interface modeling a basic game entity with methods to return current
 * position/hitbox.
 *
 */

public interface Entity {
    /**
     * This methods returns the current X coordinate of the element.
     * 
     * @return A Double representing the X coord.
     */
    Double getX();

    /**
     * This methods returns the current Y coordinate of the element.
     * 
     * @return A Double representing the Y coord.
     */
    Double getY();

    /**
     * Sets the new coordinate for the X.
     * 
     * @param x
     *            The new X.
     */
    void setX(Double x);

    /**
     * Sets the new coordinate for the Y.
     * 
     * @param y
     *            The new Y.
     */
    void setY(Double y);

    /**
     * This method returns the element's hitbox.
     * 
     * @return A geometric figure representing the element's hitbox.
     */
    Rectangle getHitbox();

    /**
     * This method returns a point in double precision.
     * 
     * @return A point containing both the X and Y coordinate of the entity.
     */
    Point2D getPosition();

    /**
     * A method to determine if an {@link Entity} is colliding with another Entity.
     * 
     * @param entity
     *            The Entity with whom to check collision.
     * @return True if there is a collision, false otherwise.
     */
    boolean isColliding(Entity entity);

}
