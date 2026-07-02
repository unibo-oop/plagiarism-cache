package it.unibo.scat.control;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.GameState;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.control.gameloop.GameLoop;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.view.api.ViewInterface;

/**
 * The main class for the "Control" section of the MVC pattern.
 */
@SuppressFBWarnings(value = "EI2", justification = "Intentional shared reference.")
public class Control implements ControlInterface {
    private final ViewInterface viewInterface;
    private final ModelInterface modelInterface;
    private final GameLoop gameLoop;
    private final Thread gameThread;

    /**
     * Creates the controller and initializes the game loop.
     *
     * @param vInterface view interface
     * @param mInterface model interface
     */
    public Control(final ViewInterface vInterface, final ModelInterface mInterface) {
        this.viewInterface = vInterface;
        this.modelInterface = mInterface;

        gameLoop = new GameLoop(modelInterface);
        gameThread = new Thread(gameLoop, "game-loop");

        modelInterface.setGameState(GameState.PAUSE);
    }

    /**
     * Initializes model and view, then starts the game loop thread.
     */
    public void init() {
        modelInterface.initEverything(Constants.ENTITIES_PATH, Constants.LEADERBOARD_PATH);
        viewInterface.initEverything();
        gameLoop.start();
        gameThread.start();
    }

    /**
     * Game thread starting.
     */
    @Override
    public void notifyStartGame() {
        gameLoop.resumeGame();
    }

    /**
     * Notifies that the game should be paused.
     */
    @Override
    public void notifyPauseGame() {
        modelInterface.setGameState(GameState.PAUSE);
    }

    /**
     * Notifies a player movement request.
     *
     * @param direction movement direction
     */
    @Override
    public void notifyPlayerMovement(final Direction direction) {
        modelInterface.movePlayer(direction);
    }

    /**
     * Notifies that the player has fired a shot.
     */
    @Override
    public void notifyPlayerShot() {
        modelInterface.addPlayerShot();
    }

    /**
     * Notifies that the application should terminate.
     */
    @SuppressFBWarnings(value = "DM_EXIT", justification = "Application termination is intended")
    @Override
    public final void notifyQuitGame() {
        viewInterface.closeFrame();
        System.exit(0);
    }

    /**
     * Notifies that the game should be reset.
     */
    @Override
    public void notifyResetGame() {
        modelInterface.resetGame();
        gameLoop.resumeGame();
    }

    /**
     * Notifies that the game should resume from pause.
     */
    @Override
    public void notifyResumeGame() {
        gameLoop.resumeGame();
    }

    /**
     * Notifies that the username should be set.
     *
     * @param username player username
     */
    @Override
    public void notifySetUsername(final String username) {
        modelInterface.setUsername(username);
    }

    /**
     * @return the current game state.
     * 
     */
    @Override
    public GameState getGameState() {
        return modelInterface.getGameState();
    }

}
