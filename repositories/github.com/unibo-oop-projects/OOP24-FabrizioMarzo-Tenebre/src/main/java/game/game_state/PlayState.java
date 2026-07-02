package game.game_state;

import game.game_model.game_level.IGameLevel;
import input.input_controller.InputController;
import input.input_controller.KeyboardInputController;
import view.scene.Scene;
import view.scene.scene_swing.SwingSceneTutorial;

/**
 * Represents the game state where the actual gameplay occurs.
 * <p>
 * This class manages the game loop for playing, including input processing,
 * updating the game logic, rendering, and handling transitions to the game over state.
 * </p>
 * <p>
 * It maintains the current game level and view, and handles level progression
 * and game over conditions.
 * </p>
 */
public class PlayState implements GameState {

    private static final int DELAY_BEFORE_STATE_CHANGE_MS = 500;

    private boolean changingState = false;
    private long stateChangeTime = 0;
    private String gameOverMessage = null;

    private Scene view;
    private IGameLevel gameLevel;
    private InputController contrl = new KeyboardInputController();
    private PlayStateManager managerPlayGame = new PlayStateManager();

    private final GameStateManager gameStateManager;

    /**
     * Constructs a PlayState with the given GameStateManager to manage state transitions.
     *
     * @param gsm the GameStateManager that controls the game states
     */
    public PlayState(final GameStateManager gsm) {
        this.gameStateManager = gsm;
    }

    /**
     * Sets up the play state by loading the current level.
     */
    @Override
    public void setUp() {
        loadLevel(managerPlayGame.loadCurrentLevel());
    }

    /**
     * Processes the player input and updates the survivor's input state.
     */
    @Override
    public void processInput() {
        gameLevel.getGameSurvivor().updateInput(contrl);
    }

    /**
     * Updates the game logic for the play state.
     * <p>
     * This includes updating the current level and game entities,
     * checking for level completion, checking if the survivor is dead,
     * and handling transitions to the game over state with a delay.
     * </p>
     *
     * @param elapsed the time elapsed since the last update, in milliseconds
     */
    @Override
    public void updateGame(final int elapsed) {

        if (changingState) {
            if (System.currentTimeMillis() >= stateChangeTime) {
                gameStateManager.setState(new GameOverState(gameOverMessage));
            }
            return;
        }

        gameLevel.getLevel().updateLevelState(elapsed);
        gameLevel.updateStateGameLevel();

        if (gameLevel.getLevel().isLevelCompleted()) {
            managerPlayGame.loadNextLevel().ifPresentOrElse(
                    this::loadLevel,
                    () -> prepareStateChange("All level are completed"));
        }

        if (gameLevel.getGameSurvivor().getSurvivor().isSurvivorDead()) {
            prepareStateChange("You Are DEAD");
        }
    }

    /**
     * Renders the current game view.
     */
    @Override
    public void render() {
        view.render();
    }

    /**
     * Loads a given game level and creates the associated view.
     *
     * @param level the game level to load
     */
    private void loadLevel(final IGameLevel level) {
        this.gameLevel = level;
        this.view = new SwingSceneTutorial(gameLevel, contrl, 1200, 800);
    }

    /**
     * Prepares the game to transition to the game over state after a short delay,
     * setting the game over message to display.
     *
     * @param message the game over message to display
     */
    private void prepareStateChange(final String message) {
        changingState = true;
        gameOverMessage = message;
        stateChangeTime = System.currentTimeMillis() + DELAY_BEFORE_STATE_CHANGE_MS;
    }
}
