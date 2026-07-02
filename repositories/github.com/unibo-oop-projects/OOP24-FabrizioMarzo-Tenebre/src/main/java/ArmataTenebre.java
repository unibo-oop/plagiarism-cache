import game.game_engine.GameEngine;
import game.game_state.GameStateManager;
import game.game_state.PlayState;

/**
 * Entry point for launching the game.
 * <p>
 * {@code ArmataTenebre} initializes the game environment by setting up the
 * {@link GameEngine} and the initial game state ({@link PlayState}),
 * and then starts the main game loop.
 * </p>
 */
public class ArmataTenebre {

    /**
     * Launches the game.
     * <p>
     * This method creates a {@link GameStateManager}, sets the initial state
     * to a {@link PlayState}, sets up the {@link GameEngine}, and starts the game loop.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        GameStateManager gsm = new GameStateManager();
        gsm.setState(new PlayState(gsm));

        GameEngine engine = new GameEngine();
        engine.setup(gsm);

        engine.mainLoop();
    }
}
