package it.unibo.michelito.controller.maincontroller.api;

/**
 * Represents a parent controller that manages home-related operations.
 */
public interface HomeParentController extends Controller {
    /**
     * Switches the view from the home menu to the game.
     */
    void switchToGame();
}
