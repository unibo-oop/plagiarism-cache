package unibo.exiled.model.menu;

import java.util.List;

/**
 * Represents a menu that can contain multiple menu items.
 */
public interface Menu {
    /**
     * Adds a menu item to the menu.
     *
     * @param menuItem The menu item to add.
     */
    void addMenuItem(MenuItem menuItem);

    /**
     * Gets a list of all menu items in the menu.
     *
     * @return The list of menu items.
     */
    List<MenuItem> getMenuItems();
}
