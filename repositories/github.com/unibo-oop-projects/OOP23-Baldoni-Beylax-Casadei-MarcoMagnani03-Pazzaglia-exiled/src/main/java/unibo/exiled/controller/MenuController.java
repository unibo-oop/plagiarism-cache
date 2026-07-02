package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.MenuItem;

/**
 * The controller of the in-game menu.
 */
public interface MenuController {
    /**
     * Gets the new game menu items.
     *
     * @return the menu items in new game menu.
     */
    List<MenuItem> getNewGameMenuItems();

    /**
     * Gets the in game menu items.
     *
     * @return the menu items in game menu.
     */
    List<MenuItem> getInGameMenuItems();
}
