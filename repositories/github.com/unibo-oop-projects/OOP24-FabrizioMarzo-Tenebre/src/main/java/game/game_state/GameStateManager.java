package game.game_state;

/**
 * Manages the current game state and delegates input processing,
 * game updates, and rendering to it.
 * <p>
 * This class acts as a controller for switching between different
 * game states, ensuring that the correct state's lifecycle methods
 * are called at the appropriate times.
 * </p>
 */
public class GameStateManager {

    private GameState currentState;

    /**
     * Sets the current game state to a new state and calls its setup method.
     *
     * @param newState the new GameState to set as current
     */
    public void setState(final GameState newState) {
        this.currentState = newState;
        this.currentState.setUp();
    }

    /**
     * Processes the input for the current game state if one is set.
     */
    public void processInput() {
        if (currentState != null) {
            currentState.processInput();
        }
    }

    /**
     * Updates the game logic of the current state by the elapsed time.
     *
     * @param elapsed the time elapsed since the last update, in milliseconds
     */
    public void updateGame(final int elapsed) {
        if (currentState != null) {
            currentState.updateGame(elapsed);
        }
    }

    /**
     * Renders the current game state if one is set.
     */
    public void render() {
        if (currentState != null) {
            currentState.render();
        }
    }
}
