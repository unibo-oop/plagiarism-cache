package it.unibo.michelito.controller.maincontroller.api;

/**
 * Represents a parent controller that manages game-related operations.
 */
public interface GameParentController extends Controller {
    /**
     * Switches the view from the game back to the home menu.
     */
    void switchToHome();
}
