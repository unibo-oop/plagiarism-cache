package it.tbt.model.menu.api;

import java.util.List;

/**
 * The {@code MenuModel} interface represents a menu model containing a list of menu items.
 */
public interface MenuModel {

    /**
     * Returns the title of the menu.
     *
     * @return the title of the menu
     */
    String getTitle();

    /**
     * Returns the index of the focused item in the menu.
     *
     * @return the index of the focused item
     */
    int getFocus();

    /**
     * Sets the focus to the specified item index.
     *
     * @param focus the index of the item to set as the focus
     */
    void setFocus(int focus);

    /**
     * Returns the list of menu items.
     *
     * @return the list of menu items
     */
    List<MenuItem> getItems();

    /**
     * Performs the "Explore" action.
     */
    void triggerExplore();
}
