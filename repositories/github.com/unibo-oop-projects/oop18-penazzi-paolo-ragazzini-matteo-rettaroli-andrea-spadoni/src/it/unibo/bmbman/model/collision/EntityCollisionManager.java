package it.unibo.bmbman.model.collision;

import java.awt.Rectangle;
import java.util.Set;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Collision Manager.
 */
public interface EntityCollisionManager {
    /**
     * Determines if the followed entity is in collision with one of the entity in set.
     * @param entities set of {@link Entity}
     */
    void detectCollision(Set<Entity> entities);
    /**
     * Used to notify the followed entity a collision.
     * @param receiver {@link Entity} with which the collision occurred
     * @param newPosition new entity's position to avoid continuous collisions
     */
    void notifyCollision(Entity receiver, Position newPosition);
    /**
     * Used to know if the entity(receiver) is touched by other entity (collider). 
     * @param receiver the entity that is touched
     * @param collider Entity collision component
     * @return true if receiver and collider intersect
     */
    boolean checkCollision(Entity receiver, Rectangle collider);

}
