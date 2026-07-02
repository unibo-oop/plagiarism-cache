package pvz.controller.maincontroller.api;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

/**
 * Main application controller.
 * Manages high-level transitions such as starting the app, launching a game,
 * returning to the menu, and handling the end-game screen.
 */
public interface MainController extends Controller {

    /**
     * Starts the application.
     */
    void start();

    /**
     * Starts a new game session with the given difficulty and resolution.
     *
     * @param difficulty the game difficulty
     * @param resolution the chosen game resolution
     */
    void startGame(Difficulty difficulty, Resolution resolution);

    /**
     * Returns to the main menu.
     */
    void goToMenu();

    /**
     * Navigates to the end-game screen, showing win or loss.
     *
     * @param hasWon true if the player has won, false otherwise
     * @param resolution the resolution to use for the end-game screen
     */
    void goToEndGame(boolean hasWon, Resolution resolution);
}
