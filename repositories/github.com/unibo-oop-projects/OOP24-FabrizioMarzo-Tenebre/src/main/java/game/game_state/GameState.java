package game.game_state;

/**
 * Represents the current state of the game.
 * 
 * <p>
 * This interface defines the lifecycle methods for initializing,
 * processing input, updating game logic, and rendering the game state.
 * Implementations will provide the specific behavior for each game state.
 * </p>
 */
public interface GameState {

    /**
     * Sets up the initial configuration and resources for this game state.
     * <p>
     * This method is called once before the game loop starts.
     * </p>
     */
    void setUp();

    /**
     * Processes user input or other input events.
     * <p>
     * This method is called every frame to handle input before updating game logic.
     * </p>
     */
    void processInput();

    /**
     * Updates the game logic based on the elapsed time since the last update.
     * 
     * @param elapsed the time elapsed in milliseconds since the last update
     */
    void updateGame(final int elapsed);

    /**
     * Renders the current game state to the screen.
     * <p>
     * This method is called every frame after updating the game logic.
     * </p>
     */
    void render();
}
