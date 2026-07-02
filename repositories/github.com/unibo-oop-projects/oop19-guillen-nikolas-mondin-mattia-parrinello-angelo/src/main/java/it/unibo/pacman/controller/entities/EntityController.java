package it.unibo.pacman.controller.entities;

import java.awt.Rectangle;
import java.util.Optional;

import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.utilities.EntityType;

/**
 * 
 * An interface for a simple controller that allows to use a game object easily.
 *
 */
public interface EntityController extends Renderable {
    /**
     * Used to update the settings of an {@link Entity}.
     */
    void updateView();
    /**
     * Used to find a collision.
     * 
     * @param collider the area to test the collision.
     * @return the {@link Entity} that collided (if there's a collision).
     */
    Optional<Entity> getCollision(Rectangle collider);
    /**
     * Used to remove logically the {@link Entity} from the game.
     */
    void remove();
    /**
     * Used to know if the {@link Entity} has been removed logically from the game.
     * 
     * @return true if the {@link Entity} has been removed, false otherwise
     */
    boolean isRemoved();
    /**
     * Used to know the type of the entity.
     * @return the type
     */
    EntityType getType();
}
