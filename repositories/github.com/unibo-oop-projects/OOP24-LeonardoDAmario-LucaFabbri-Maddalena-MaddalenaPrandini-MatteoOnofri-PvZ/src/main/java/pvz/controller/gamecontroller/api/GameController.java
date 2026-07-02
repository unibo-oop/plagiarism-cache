package pvz.controller.gamecontroller.api;
import pvz.controller.maincontroller.api.Controller;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

/**
 * Interface that represents the game controller responsible for managing the game loop and core logic.
 * Extends the {@link Controller} interface to integrate with the overall application flow.
 */
public interface GameController extends Controller {

    /**
     * Starts a new game session with the given difficulty and resolution.
     *
     * @param difficulty the game difficulty level
     * @param resolution the screen resolution
     */
    void startGame(Difficulty difficulty, Resolution resolution);

    /**
     * Stops the current game session, halting updates and rendering.
     */

    void stopGame();
}
