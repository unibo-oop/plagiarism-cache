package clashclass.engine;

/**
 * Represents a GameLoop runnable on a different Thread.
 */
public interface GameLoop extends Runnable {
    /**
     * Sets the active scene of the game.
     *
     * @param scene the current active scene
     */
    void setCurrentScene(GameScene scene);
}
