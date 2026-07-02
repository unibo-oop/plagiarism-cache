package ballblast.model.gameobjects;

import java.util.List;

/**
 * Handles all {@link GameObject}s of a specific Level.
 */
public interface GameObjectManager {

    /**
     * Updates the status of every {@link GameObject}.
     * 
     * @param elapsed the time elapsed since last update.
     */
    void update(double elapsed);

    /**
     * Adds a {@link GameObject} {@link List}.
     * 
     * @param gameObjects the {@link GameObject} {@link List} to be added.
     */
    void addGameObjects(List<GameObject> gameObjects);

    /**
     * Gets the {@link List} containing all {@link GameObject}s.
     * 
     * @return a defensive copy of the {@link List} containing all {@link GameObject}s.
     */
    List<GameObject> getGameObjects();
}
