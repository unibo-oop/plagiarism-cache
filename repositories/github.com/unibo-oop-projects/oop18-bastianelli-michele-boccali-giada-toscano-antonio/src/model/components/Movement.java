package model.components;

import org.jbox2d.common.Vec2;

/**
 * Interface to represents a movement component.
 */
public interface Movement {

    /**
     * Move the entity to the desired direction.
     * 
     * @param direction the direction of the movement to be applied.
     */
    void move(Vec2 movementValue);
}
