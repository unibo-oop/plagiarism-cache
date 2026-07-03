package model.entities;

import java.io.Serializable;

import model.hitbox.Hitbox;

/**
 * 
 * Represents all the entities that can be created in the game.
 *
 * @param <T> The type of Hitbox used of this entity.
 */
public interface Entity<T extends Hitbox> extends Serializable {

    /**
     * Get the Hitbox used by this entity.
     * 
     * @return The Hitbox.
     */
    T getHitbox();
}
