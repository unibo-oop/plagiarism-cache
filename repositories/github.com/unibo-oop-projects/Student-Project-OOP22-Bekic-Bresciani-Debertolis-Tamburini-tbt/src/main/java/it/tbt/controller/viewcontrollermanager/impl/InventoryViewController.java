package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.InventoryState;
import java.awt.event.KeyEvent;

/**
 * The {@code InventoryViewController} class represents the view controller for the inventory state.
 * It handles user input and triggers actions associated with the inventory state.
 */
public class InventoryViewController extends AbstractViewController {

    private final InventoryState inventoryState;

    /**
     * Constructs a new {@code InventoryViewController} with the specified inventory state.
     *
     * @param inventoryState the inventory state
     * @throws IllegalArgumentException if inventoryState is null
     */
    public InventoryViewController(final InventoryState inventoryState) {
        super();
        if (inventoryState == null) {
            throw new IllegalArgumentException("InventoryState cannot be null");
        }
        this.inventoryState = inventoryState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.addCommand(() -> inventoryState.previousElement());
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.addCommand(() -> inventoryState.nextElement());
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> this.addCommand(() -> inventoryState.performAction());
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> this.addCommand(() -> inventoryState.nextPhase());
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> this.addCommand(() -> inventoryState.previousPhase());
            case KeyEvent.VK_ESCAPE -> this.addCommand(() -> inventoryState.switchToExplore());
            default -> { }
        }
    }

}
