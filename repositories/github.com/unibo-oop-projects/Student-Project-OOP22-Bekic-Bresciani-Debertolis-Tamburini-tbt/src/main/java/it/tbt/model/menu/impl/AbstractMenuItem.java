package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuItem;

/**
 * The {@code MenuItem} class represents a menu item with text.
 */
public abstract class AbstractMenuItem implements MenuItem {

    private final String text;

    /**
     * Constructs a new {@code MenuItem} with the specified text.
     *
     * @param text the text of the menu item
     */
    public AbstractMenuItem(final String text) {
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text;
    }
}
