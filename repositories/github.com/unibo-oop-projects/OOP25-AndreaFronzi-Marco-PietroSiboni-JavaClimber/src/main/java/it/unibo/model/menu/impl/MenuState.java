package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.AbstractMenuState;

/**
 * Represents the main menu state.
 * This is typically the first screen the user interacts with.
 */
public class MenuState extends AbstractMenuState {

    /**
     * Constructs a new MenuState.
     * 
     * @param menu the Menu context
     */
    public MenuState(final Menu menu) {
        super(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.menu.getMainController().openMenuView();
    }

}
