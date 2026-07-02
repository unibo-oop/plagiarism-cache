package pvz.controller.maincontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.controller.endgamecontroller.impl.EndGameControllerImpl;
import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.impl.GameControllerImpl;
import pvz.controller.maincontroller.api.MainController;
import pvz.controller.menucontroller.api.MenuController;
import pvz.controller.menucontroller.impl.MenuControllerImpl;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the {@link MainController} interface.
 * Manages the flow of the application: menu, game, end-game, and error handling.
 */
public final class MainControllerImpl implements MainController {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(MainControllerImpl.class.getName());

    /** The game controller instance. */
    private final GameController gameController = new GameControllerImpl(this);

    /** The menu controller instance. */
    private final MenuController menuController = new MenuControllerImpl(this);

    /** The end-game controller instance. */
    private final EndGameController endGameController = new EndGameControllerImpl(this);

    /**
     * Starts the application by opening the main menu.
     */
    @Override
    public void start() {
        menuController.openMenu();
    }

    /**
     * Starts the game with the given difficulty and resolution.
     *
     * @param difficulty the selected game difficulty
     * @param resolution the selected resolution
     */
    @Override
    public void startGame(final Difficulty difficulty, final Resolution resolution) {
        gameController.startGame(difficulty, resolution);
    }

    /**
     * Opens the main menu view.
     */
    @Override
    public void goToMenu() {
        menuController.openMenu();
    }

    /**
     * Navigates to the end-game screen.
     *
     * @param hasWon true if the player has won, false otherwise
     * @param resolution the resolution to use for the end-game screen
     */
    @Override
    public void goToEndGame(final boolean hasWon, final Resolution resolution) {
        endGameController.openEndGameMenu(hasWon, resolution);
    }

    /**
     * Handles any uncaught exceptions, logs the error, and exits the application.
     *
     * @param exception the exception to handle
     */
    @Override
    public void handleException(final Exception exception) {
        LOGGER.log(Level.SEVERE, "Unexpected error occurred, the game will be closed. See the log for more info.", exception);
        this.quit();
    }

    /**
     * Quits the application by closing the main game, menu, and end-game views.
     * <p>
     * This method ensures that all relevant controllers properly shut down their respective
     * components, releasing resources and stopping any active game processes before exit.
     */
    @Override
    public void quit() {
        menuController.closeMenu();
        endGameController.closeEndGameMenu();
    }

}
