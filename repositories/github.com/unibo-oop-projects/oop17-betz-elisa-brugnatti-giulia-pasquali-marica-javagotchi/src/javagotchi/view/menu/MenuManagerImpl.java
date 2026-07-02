package javagotchi.view.menu;

import javagotchi.controller.menu.MenuController;

/**
 * Implementations of the Menu Manager.
 * This class hasn't the reference of all the menus: 
 * the information menu, the saved avatar menu and the initialization menu 
 * are created every time new.
 * 
 * @author giulia
 *
 */
public class MenuManagerImpl implements MenuManager {
    private final MenuController controller;
    private final MenuView view;
    private final StartMenu startMenu;
    private final MainMenu mainMenu;
    
    /**
     * this is the constructor for this class.
     * @param controller **this the MenuController**
     * @param view  **this the MenuView**
     */
    public MenuManagerImpl(final MenuController controller, final MenuView view) {
        this.view = view;
        this.controller = controller;
        this.startMenu = new StartMenu(this.controller, view);
        this.mainMenu = new MainMenu(this.view);
    }

    @Override
    public final void showStartMenu() {
        this.startMenu.show();
    }

    @Override
    public final void showInitMenu() {
        new InitMenu(controller, view).show();
    }

    @Override
    public final void showMainMenu() {
        this.mainMenu.show();
    }

    @Override
    public final void showSavedAvatarMenu() {
        new SavedAvatarMenu(controller, view).show();
    }

    @Override
    public final void showInformationMenu() {
        new InformationMenu(controller, view).show();
    }
}
