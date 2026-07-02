package it.unibo.michelito.controller.homecontroller.api;

import it.unibo.michelito.controller.maincontroller.api.Controller;

/**
 * Represents a controller for managing the home menu of the game.
 * This controller is responsible for switching to the game view.
 */
public interface ViewControllerListener extends Controller {
    /**
     * Request to switch to the game.
     */
    void switchToGame();
}
