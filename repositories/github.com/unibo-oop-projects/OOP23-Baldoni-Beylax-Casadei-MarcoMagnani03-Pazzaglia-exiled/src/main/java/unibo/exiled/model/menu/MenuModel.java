package unibo.exiled.model.menu;

/**
 * The model that manages the in-game menu.
 */
public interface MenuModel {
    /**
     * Gets the new game menu.
     *
     * @return the new game menu.
     */
    Menu getNewGameMenu();

    /**
     * Gest the in game menu.
     *
     * @return the in game menu.
     */
    Menu getInGameMenu();
}
