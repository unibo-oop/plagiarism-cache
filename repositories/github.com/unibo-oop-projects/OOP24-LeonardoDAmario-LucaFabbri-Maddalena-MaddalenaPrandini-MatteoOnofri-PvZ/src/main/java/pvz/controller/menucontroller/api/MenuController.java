package pvz.controller.menucontroller.api;

import pvz.controller.maincontroller.api.Controller;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

/**
 * Interface for the controller that manages the game menu.
 * Handles opening, closing, starting the game, and showing the tutorial.
 */
public interface MenuController extends Controller {

    /**
     * Opens the main menu view.
     */
    void openMenu();

    /**
     * Closes the main menu view.
     */
    void closeMenu();

    /**
     * Starts the game with the given difficulty and resolution.
     *
     * @param difficulty the game difficulty selected by the user
     * @param resolution the chosen resolution for the game window
     */
    void startGame(Difficulty difficulty, Resolution resolution);

    /**
     * Shows the tutorial view for the game.
     *
     * @param currentResolution the current resolution to use for the tutorial window
     */
    void showTutorialView(Resolution currentResolution);
}
