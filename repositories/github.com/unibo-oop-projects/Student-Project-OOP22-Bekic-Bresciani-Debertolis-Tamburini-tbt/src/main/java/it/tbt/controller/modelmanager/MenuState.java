package it.tbt.controller.modelmanager;

import it.tbt.model.menu.api.MenuItem;

import java.util.List;

/**
 * The {@code MenuState} interface represents the state of a menu in the application's controller.
 * It provides methods to navigate through the menu options and trigger actions.
 */
public interface MenuState extends ModelState {
    /**
     * Moves the focus to the next menu item.
     */
    void nextElement();

    /**
     * Moves the focus to the previous menu item.
     */
    void previousElement();

    /**
     * Returns the list of menu items.
     *
     * @return the list of menu items
     */
    List<MenuItem> getItems();

    /**
     * Returns the index of the currently focused menu item.
     *
     * @return the index of the focused menu item
     */
    int getFocus();

    /**
     * Triggers the action associated with the selected menu item.
     */
    void triggerExplore();

    /**
     * Returns the title of the menu.
     *
     * @return the title of the menu
     */
    String getTitle();
}
