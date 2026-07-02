package unibo.exiled.model.menu;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Menu interface that represents a menu containing
 * multiple menu items.
 */
@Immutable
public class MenuImpl implements Menu {
    private final List<MenuItem> menuItems;

    /**
     * Constructs a new MenuImpl with an empty list of menu items.
     */
    public MenuImpl() {
        this.menuItems = new ArrayList<>();
    }

    /**
     * Adds a menu item to the menu.
     *
     * @param menuItem The menu item to add.
     */
    @Override
    public void addMenuItem(final MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    /**
     * Gets a list of all menu items in the menu.
     *
     * @return The list of menu items.
     */
    @Override
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(this.menuItems);
    }
}
