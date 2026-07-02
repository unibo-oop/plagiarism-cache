package it.tbt.controller.modelmanager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.menu.api.MenuModel;

import java.util.List;

/**
 * The {@code MenuStateImpl} class represents the implementation of the {@link MenuState} interface.
 * It manages the state of a menu in the application's controller.
 */
public class MenuStateImpl implements MenuState {
    private final MenuModel menuModel;

    /**
     * Constructs a new {@code MenuStateImpl} with the specified menu model.
     *
     * @param menu the menu model
     * @throws IllegalArgumentException if the menu is null
     */
    @SuppressFBWarnings(
            value = {
                    "EI2"
            },
            justification = "Menu have to get the exact instance of MenuModel"
    )
    public MenuStateImpl(final MenuModel menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Menu cannot be null");
        }
        this.menuModel = menu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextElement() {
        final List<MenuItem> items = menuModel.getItems();
        if (items.isEmpty()) {
            throw new IllegalStateException("Menu has no items");
        }
        final int focus = menuModel.getFocus();
        menuModel.setFocus((focus + 1) % items.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void previousElement() {
        final List<MenuItem> items = menuModel.getItems();
        if (items.isEmpty()) {
            throw new IllegalStateException("Menu has no items");
        }
        final int focus = menuModel.getFocus();
        menuModel.setFocus((focus - 1) < 0 ? items.size() - 1 : focus - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MenuItem> getItems() {
        return menuModel.getItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFocus() {
        return menuModel.getFocus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerExplore() {
        if (menuModel == null) {
            throw new IllegalStateException("Menu model not set");
        }
        menuModel.triggerExplore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return menuModel.getTitle();
    }
}
