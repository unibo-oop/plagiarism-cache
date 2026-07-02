package it.unibo.model.menu.impl;

import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.api.AbstractMenuState;

/**
 * Represents the inventory state of the menu.
 * Allows the user to view their items and gadgets.
 */
public class InventoryState extends AbstractMenuState {

    /**
     * Constructs a new InventoryState.
     * 
     * @param menu the Menu context
     */
    public InventoryState(final Menu menu) {
        super(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.menu.getMainController().openInventoryView();
    }

}
