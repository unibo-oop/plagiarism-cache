package pvz.model.game.api;

import pvz.model.entities.api.Entity;

import java.util.Set;

/**
 * Interface for managing entities in the game.
 * <p>
 * The {@code EntitiesManager} is responsible for tracking all game entities,
 * managing sun points, kill count, and handling the spawning of zombies.
 */
public interface EntitiesManager {

    /**
     * Adds a new entity to the game.
     *
     * @param entity the entity to be added; must not be {@code null}.
     */
    void addEntity(Entity entity);

    /**
     * Removes an existing entity from the game.
     *
     * @param entity the entity to be removed; must not be {@code null}.
     */
    void removeEntity(Entity entity);

    /**
     * Retrieves all the entities currently in the game.
     *
     * @return a {@code Set} of all active entities.
     */
    Set<Entity> getEntities();

    /**
     * Increases the player's sun points by the specified amount.
     *
     * @param amount the number of sun points to add; must be non-negative.
     */
    void addSun(int amount);

    /**
     * Decreases the player's sun points by the specified amount.
     *
     * @param amount the number of sun points to subtract; must be non-negative.
     * @return {@code true} if the sun points were successfully deducted;
     *         {@code false} if there were not enough points.
     */
    boolean decreaseSun(int amount);

    /**
     * Increments the number of zombies killed by the player.
     */
    void addKill();

    /**
     * Returns the total number of zombies killed.
     *
     * @return the kill count.
     */
    int getKillCount();

    /**
     * Returns the current amount of sun points the player has.
     *
     * @return the number of available sun points.
     */
    int getSunCount();
}
