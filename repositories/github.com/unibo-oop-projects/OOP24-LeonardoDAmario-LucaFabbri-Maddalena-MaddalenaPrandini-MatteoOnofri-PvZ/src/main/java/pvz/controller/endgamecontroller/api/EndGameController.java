package pvz.controller.endgamecontroller.api;

import pvz.controller.maincontroller.api.Controller;
import pvz.utilities.Resolution;

/**
 * Interface for the controller that manages the end-game menu.
 * Handles opening and closing of the end-game screen.
 */
public interface EndGameController extends Controller {

    /**
     * Opens the end-game menu showing victory or defeat.
     *
     * @param hasWon true if the player has won, false otherwise
     * @param resolution the resolution to use for the end-game screen
     */
    void openEndGameMenu(boolean hasWon, Resolution resolution);

    /**
     * Closes the end-game menu and returns to the main menu.
     */
    void closeEndGameMenu();

    /**
     * Opens the main menu view.
     */
    void goToMenu();
}
