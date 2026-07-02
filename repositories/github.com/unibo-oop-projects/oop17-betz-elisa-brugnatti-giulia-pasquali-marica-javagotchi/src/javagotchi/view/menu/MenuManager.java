package javagotchi.view.menu;

/**
 * This is the interface for the Manager of the menus.
 *
 * @author giulia
 *
 */
public interface MenuManager {
    /**
     * this method show the first menu.
     *         Options: New game, Resume (the prevoius one), Exit
     */
    void showStartMenu();
    /**
     *this method show the information about the Javagotchi saved.
     */
    void showInformationMenu();

    /**
     * this method show the menu that allows the user initialize the Javagotchi's field.
     * Options: Name, Gender(female/ male), Avatar(fox/ cat/ panda)
     */
    void showInitMenu();
    /**
     * this method show the main menu.
     * Options: Saved Avatar, Info, Exit
     */
    void showMainMenu();
    /**
     * this method show the saved avatar.
     * Options: Delete, Create a Javagotchi, or Play with it
     */
    void showSavedAvatarMenu();
}
