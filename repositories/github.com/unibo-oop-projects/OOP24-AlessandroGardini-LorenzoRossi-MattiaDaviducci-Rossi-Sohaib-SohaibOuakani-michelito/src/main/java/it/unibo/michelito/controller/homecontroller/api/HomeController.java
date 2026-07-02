package it.unibo.michelito.controller.homecontroller.api;

import it.unibo.michelito.controller.maincontroller.api.Controller;

/**
 * Represents a controller for managing the home menu of the game.
 * Provides methods to display and hide the game menu.
 */
public interface HomeController extends Controller {
    /**
     * Displays the home menu.
     * This method is responsible for rendering the menu on the screen.
     */
    void showMenu();

    /**
     * Hides the home menu.
     * This method is responsible for removing the menu from the screen.
     */
    void hideMenu();
}
