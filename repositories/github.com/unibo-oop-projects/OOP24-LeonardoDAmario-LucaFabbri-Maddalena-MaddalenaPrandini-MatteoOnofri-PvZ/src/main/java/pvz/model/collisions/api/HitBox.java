package pvz.model.collisions.api;

import pvz.utilities.Position;

/**
 * Represents a hitbox for an entity, used for collision detection.
 * Implementations define the shape and logic for detecting overlaps with other hitboxes.
 */
public interface HitBox {

    /**
     * Checks if this hitbox is colliding with another hitbox.
     *
     * @param hitbox the other hitbox to check collision with
     * @return true if the hitboxes are colliding, false otherwise
     */
    boolean isColliding(HitBox hitbox);

    /**
     * Updates the position of this hitbox.
     *
     * @param pos the new position to set
     */
    void update(Position pos);

    /**
     * Gets the x-coordinate of the hitbox.
     *
     * @return the x-coordinate
     */
    double getX();

    /**
     * Gets the width of the hitbox.
     *
     * @return the width
     */
    double getWidth();
}

