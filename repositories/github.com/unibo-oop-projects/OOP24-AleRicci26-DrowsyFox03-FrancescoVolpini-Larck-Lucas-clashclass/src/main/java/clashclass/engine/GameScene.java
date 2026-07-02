package clashclass.engine;

import clashclass.ecs.GameObject;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Represents a single scene in the game.
 */
public interface GameScene {
    /**
     * Applies a function to all the GameObjects listed in the scene.
     *
     * @param consumer the function called for every GameObject
     */
    void traverseGameObjects(Consumer<GameObject> consumer);

    /**
     * Updates all the GameObjects that implement UpdateProvider.
     *
     * @param deltaTime time elapsed between the previous and the current frame
     */
    void updateGameObjects(float deltaTime);

    /**
     * Checks for game object marked as destroyed, and destroys them (if any).
     */
    void checkForDestroyedGameObjects();

    /**
     * Adds a GameObject to the scene.
     *
     * @param gameObject the GameObject to add
     */
    void addGameObject(GameObject gameObject);

    /**
     * Gets all the game objects in the scene.
     *
     * @return the set of game objects
     */
    Set<GameObject> getGameObjects();

    /**
     * Gets a copy of all the game objects in the scene.
     *
     * @return the copy of the set of game objects
     */
    Set<GameObject> getGameObjectsCopy();
}
