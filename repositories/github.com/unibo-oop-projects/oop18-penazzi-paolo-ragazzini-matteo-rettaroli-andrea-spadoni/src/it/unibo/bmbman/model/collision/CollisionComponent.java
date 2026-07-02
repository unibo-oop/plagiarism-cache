package it.unibo.bmbman.model.collision;

import java.awt.Rectangle;

import it.unibo.bmbman.model.entities.Entity;
/**
 * Interface for collision component.
 */
public interface CollisionComponent {

    /**
     * Used to get the entity associated.
     * @return {@link Entity}
     */
    Entity getFollowedEntity();
    /**
     * Used to determine the area occupied by the entity.
     * @return a {@link Rectangle}
     */
    Rectangle getHitbox();
    /**
     * Used to determine the area occupied by the entity 
     * while it is moving up.
     * @return a {@link Rectangle}
     */
    Rectangle getTopHitbox();
    /**
     * Used to determine the area occupied by the entity
     * while it is moving down.
     * @return a {@link Rectangle}
     */
    Rectangle getBottomHitbox();
    /**
     * Used to determine the area occupied by the entity
     * while it is moving left.
     * @return a {@link Rectangle}
     */
    Rectangle getLeftHitbox();
    /**
     * Used to determine the area occupied by the entity
     * while it is moving right.
     * @return a {@link Rectangle}
     */
    Rectangle getRightHitbox();
    /**
     * Used to notify the entity a {@link Collision}.
     * @param c {@link Collision}
     */
    void notifyCollision(Collision c);
}
