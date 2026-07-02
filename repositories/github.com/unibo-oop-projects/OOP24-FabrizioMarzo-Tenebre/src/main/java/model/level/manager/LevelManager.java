package model.level.manager;

/**
 * Interface for managing the game logic and progression of a level.
 * <p>
 * Responsible for updating the level state, spawning entities, and checking
 * completion conditions.
 * </p>
 */
public interface LevelManager {

    /**
     * Updates the internal state of the level based on the elapsed time.
     * <p>
     * This method should be called regularly (e.g., every frame)
     * to process spawning, entity actions, and level progression.
     *
     * @param dt the time delta in milliseconds since the last update.
     */
    void update(final int dt);
}
