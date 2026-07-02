package pvz.controller.endgamecontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.controller.maincontroller.api.MainController;
import pvz.utilities.Resolution;
import pvz.view.endgameview.impl.EndGameViewImpl;

/**
 * Implementation of the {@link EndGameController} interface.
 * Handles logic for the end-game menu and transitions to and from it.
 */
public final class EndGameControllerImpl implements EndGameController {

    /** The parent main controller. */
    private final MainController parentController;

    /** The view for the end-game screen. */
    private EndGameViewImpl view;

    /**
     * Constructs the end-game controller with the given parent controller.
     *
     * @param controller the parent MainController instance
     */
    public EndGameControllerImpl(final MainController controller) {
        this.parentController = controller;
    }

    /**
     * Opens the end-game menu.
     *
     * @param hasWon true if the player has won, false otherwise
     * @param resolution the resolution to use for the end-game screen
     */
    @Override
    public void openEndGameMenu(final boolean hasWon, final Resolution resolution) {
        this.view = new EndGameViewImpl(this, hasWon, resolution);
    }

    /**
     * Closes the end-game menu and returns to the main menu.
     */
    @Override
    public void closeEndGameMenu() {
        if (view != null) {
            view.close();
        }
    }

    /**
     * Opens the main menu view.
     */
    @Override
    public void goToMenu() {
        parentController.goToMenu();
        if (view != null) {
            view.close();
        }
    }

    /**
     * Handles exceptions by delegating to the parent controller.
     *
     * @param exception the exception to handle
     */
    @Override
    public void handleException(final Exception exception) {
        parentController.handleException(exception);
    }

    /**
     * Requests the termination of the application by delegating
     * the operation to the parent controller.
     * <p>
     * This method propagates the quit command to the main controller,
     * which is responsible for properly shutting down the application.
     */
    @Override
    public void quit() {
        parentController.quit();
    }
}
