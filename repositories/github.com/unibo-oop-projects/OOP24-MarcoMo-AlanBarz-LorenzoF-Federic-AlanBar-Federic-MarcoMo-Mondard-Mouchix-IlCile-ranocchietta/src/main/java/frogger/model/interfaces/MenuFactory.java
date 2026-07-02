package frogger.model.interfaces;

/**
 * Factory interface for creating different types of menus in the game.
 * This interface defines methods to create the main menu, pause menu, and death menu.
 */
public interface MenuFactory {
    /**
     * Creates the main menu with options to play, shop, or quit.
     * @return the Main Menu
     */
    Menu mainMenu();

    /**
     * Creates the pause menu with options to resume playing, return to the main menu, or quit.
     * @return the Pause Menu
     */
    Menu pauseMenu();

    /**
     * Creates the death menu with options to resume playing, return to the main menu, or quit.
     * @return the Death Menu
     */
    Menu deathMenu();

}
