package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.AbstractMenuState;

/**
 * Represents the shopping state of the menu.
 * Handles the logic for purchasing items or upgrades.
 */
public class ShoppingState extends AbstractMenuState {

    /**
     * Constructs a new ShoppingState.
     * 
     * @param menu the Menu context
     */
    public ShoppingState(final Menu menu) {
        super(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.menu.getMainController().openShopView();
    }

}
