package pvz.model.entities.api;

import pvz.model.collisions.api.HitBox;
import pvz.model.game.api.EntitiesManager;
import pvz.utilities.Position;

/**
 * Represents a generic entity in the game.
 * <p>
 * All game entities, such as plants, zombies, and bullets, must implement this interface.
 * Defines essential behaviors like position management, collision detection, and state updates.
 */
public interface Entity {

    /**
     * Sets the position of the entity.
     *
     * @param position the new position; must not be {@code null}.
     */
    void setPosition(Position position);

    /**
     * Retrieves the current position of the entity.
     *
     * @return the current {@link Position}.
     */
    Position getPosition();

    /**
     * Updates the state of the entity based on the elapsed time.
     *
     * @param deltaTime the time since the last update in milliseconds.
     * @param entitiesManager the manager responsible for handling all game entities.
     */
    void update(long deltaTime, EntitiesManager entitiesManager);

    /**
     * Returns the hitbox used for collision detection.
     *
     * @return the {@link HitBox} of this entity.
     */
    HitBox getHitBox();
}
