package controller;

/**
 * 
 * Interface to control menus.
 *
 */
public interface MenuController extends OptionsController {

    /**
     * Go to Main menu.
     */
    void goToMainMenu();

    /**
     * Go to Game.
     */
    void goToNewGame();

    /**
     * Go to Leaderboards menu.
     */
    void goToLeaderboards();

    /**
     * Go to Options menu.
     */
    void goToOptions();

    /**
     * Go to Credits menu.
     */
    void goToCredits();

    /**
     * Quit application.
     */
    void quit();
}
