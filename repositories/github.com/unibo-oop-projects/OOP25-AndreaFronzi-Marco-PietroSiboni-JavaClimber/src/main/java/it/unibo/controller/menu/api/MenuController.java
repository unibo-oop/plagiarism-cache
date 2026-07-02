package it.unibo.controller.menu.api;

import it.unibo.view.menu.api.MenuView;

/**
 * Controller for the menu view, responsible for handling user interactions and
 * updating the menu state accordingly.
 */
public interface MenuController {

    /**
     * Set the view for this controller and refresh it with current data.
     * 
     * @param view the menu view to set
     */
    void setView(MenuView view);

    /**
     * Open the game view to start a new game session.
     */
    void start();

    /**
     * Open the shop view.
     */
    void shop();

    /**
     * Open the inventory view.
     */
    void inventory();

    /**
     * Exit the application.
     */
    void exit();

    /**
     * Open the menu view.
     */
    void openViewMenu();

    /**
     * Get the current high score.
     * 
     * @return the current high score
     */
    int getHighScore();

}
