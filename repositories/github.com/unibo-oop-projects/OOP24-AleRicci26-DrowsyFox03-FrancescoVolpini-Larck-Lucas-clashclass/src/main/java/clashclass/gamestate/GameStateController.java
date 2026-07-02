package clashclass.gamestate;

/**
 * Represents a state manager used by {@link GameStateManager}.
 */
public interface GameStateController {
    /**
     * Sets the game state manager reference.
     *
     * @param gameStateManager the game state manager reference
     */
    void setGameStateManager(GameStateManager gameStateManager);

    /**
     * Clears the scene.
     */
    void clearScene();
}
