package it.tbt.model.menu.impl;

import it.tbt.model.menu.api.MenuButton;

/**
 * The {@code MenuButton} class represents a button in a menu.
 * It extends the {@link AbstractMenuItem} class and implements the {@link MenuButton} interface.
 */
public abstract class AbstractMenuButton extends AbstractMenuItem implements MenuButton {

    /**
     * Constructs a {@code MenuButton} with the specified text.
     *
     * @param text the text of the menu button
     */
    public AbstractMenuButton(final String text) {
        super(text);
    }
}
