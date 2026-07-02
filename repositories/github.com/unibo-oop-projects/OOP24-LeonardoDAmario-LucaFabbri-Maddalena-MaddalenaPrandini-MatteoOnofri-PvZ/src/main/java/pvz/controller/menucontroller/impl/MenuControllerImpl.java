package pvz.controller.menucontroller.impl;

import pvz.controller.maincontroller.api.MainController;
import pvz.controller.menucontroller.api.MenuController;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;
import pvz.view.menuview.impl.MenuViewImpl;
import pvz.view.menuview.impl.TutorialView;

/**
 * Implementation of the {@link MenuController} interface.
 * Handles the logic for opening/closing the menu, starting the game,
 * and showing the tutorial view.
 */
public final class MenuControllerImpl implements MenuController {

    /** The menu view instance. */
    private MenuViewImpl view;

    /** The parent main controller. */
    private final MainController parentController;

    /**
     * Constructs a new MenuControllerImpl with the given parent controller.
     *
     * @param mainController the MainController instance
     */
    public MenuControllerImpl(final MainController mainController) {
        this.parentController = mainController;
    }

    /**
     * Opens the main menu.
     */
    @Override
    public void openMenu() {
        this.view = new MenuViewImpl(this);
    }

    /**
     * Closes the main menu, if open.
     */
    @Override
    public void closeMenu() {
        if (this.view != null) {
                this.view.close();
            this.view = null;
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

    /**
     * Starts the game with the given difficulty and resolution,
     * then closes the menu.
     *
     * @param difficulty the selected difficulty
     * @param resolution the selected resolution
     */
    @Override
    public void startGame(final Difficulty difficulty, final Resolution resolution) {
        parentController.startGame(difficulty, resolution);
        this.closeMenu();
    }

    /**
     * Shows the tutorial view with the given resolution.
     *
     * @param resolution the resolution for the tutorial window
     */
    @Override
    public void showTutorialView(final Resolution resolution) {
        new TutorialView(resolution);
    }


}
