package it.unibo.controller.app.api;

import it.unibo.model.menu.api.Menu;
import it.unibo.view.app.api.MainView;

/**
 * Interface for the main controller, which manages the main menu and the
 * transition between different views.
 */
public interface MainController {

    /**
     * Set the view for this controller and refresh it with current data.
     * 
     * @param view the main view to set
     */
    void setView(MainView view);

    /**
     * Open the main menu view.
     */
    void openMenuView();

    /**
     * Launch the game.
     */
    void launchGame();

    /**
     * Open the inventory view.
     */
    void openInventoryView();

    /**
     * Open the shop view.
     */
    void openShopView();

    /**
     * Open the end view.
     */
    void openEndView();

    /**
     * Open the pause view.
     */
    void openPauseView();

    /**
     * Save the current game progress.
     */
    void saveProgress();

    /**
     * Get the menu.
     * 
     * @return the menu
     */
    Menu getMenu();
}
