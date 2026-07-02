package it.unibo.bmbman.model.collision;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.Position;
/**
 * The interface for collision between two entities in our game.
 */
public interface Collision {
    /**
     * Used to get the other entity of the collision.
     * @return {@link Entity}
     */
    Entity getReceiver();
    /**
     * Used to know where entities collide.
     * @return {@link Position}
     */
    Position getPosition();
}
