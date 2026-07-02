package common.events;

import controller.entities.Entity;
import controller.entities.Player;
import model.CollisionHandler.CollisionSide;

/**
 * An Event generated when a collision occurs.
 */
public class CollisionEvent implements Event {

    private final CollisionSide collisionSide;
    private final Player player;
    private final Entity entityB;

    /**
     * Create a collision event.
     * @param player the player entity
     * @param entityB the entity that the player collided with
     * @param collisionSide the collision side
     */
    public CollisionEvent(final Player player, final Entity entityB, final CollisionSide collisionSide) {
        this.player = player;
        this.entityB = entityB;
        this.collisionSide = collisionSide;
    }

    /**
     * @return the collision side
     */
    public CollisionSide getCollisionSide() {
        return collisionSide;
    }

    /**
     * @return the player who collided.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the entity that collided with the player.
     */
    public Entity getEntityB() {
        return entityB;
    }

}
