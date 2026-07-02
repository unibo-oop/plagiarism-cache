package clashclass.engine;

import clashclass.ecs.GameObject;

/**
 * Represents the game engine, which holds all the GameObjects in a specific scene.
 */
public interface GameEngine {
    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Stops the game loop.
     */
    void stop();

    /**
     * Adds a GameObject to the current scene.
     *
     * @param gameObject the GameObject to add
     */
    void addGameObject(GameObject gameObject);
}
