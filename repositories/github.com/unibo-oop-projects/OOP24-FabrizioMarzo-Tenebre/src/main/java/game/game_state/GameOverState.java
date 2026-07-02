package game.game_state;

import view.scene.Scene;
import view.scene.scene_swing.SwingSceneGameOver;

/**
 * Represents the game over state, displayed when the game ends.
 * <p>
 * This state shows the final message indicating the result of the game
 * (e.g., level completed or player death) and renders the game over scene.
 * </p>
 */
public class GameOverState implements GameState {

    private Scene scene;
    private String finishLevel;

    /**
     * Creates a GameOverState with the specified final message.
     * 
     * @param finishLevel the message to display on the game over screen
     */
    public GameOverState(final String finishLevel) {
        this.finishLevel = finishLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        scene = new SwingSceneGameOver(1200, 800, finishLevel);
    }

    /**
     * {@inheritDoc}
     * <p>
     * No input processing is required in the game over state.
     * </p>
     */
    @Override
    public void processInput() {
        // No input to process in game over state
    }

    /**
     * {@inheritDoc}
     * <p>
     * No game logic updates are performed in the game over state.
     * </p>
     */
    @Override
    public void updateGame(final int elapsed) {
        // No updates in game over state
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        scene.render();
    }
}
