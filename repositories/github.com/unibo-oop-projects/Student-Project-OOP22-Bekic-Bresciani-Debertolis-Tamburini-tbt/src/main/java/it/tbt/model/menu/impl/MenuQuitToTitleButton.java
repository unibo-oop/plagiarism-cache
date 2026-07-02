package it.tbt.model.menu.impl;

import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

/**
 * The {@code MenuQuitToTitleButton} class represents a menu button for quitting to the title screen.
 * It extends the {@link AbstractMenuButton} class and implements the {@link StateTrigger} interface.
 */
public class MenuQuitToTitleButton extends  AbstractMenuButton implements StateTrigger {

    private StateObserver stateObserver;

    /**
     * Creates a new instance of {@code MenuQuitToTitleButton} with the specified text.
     *
     * @param text the text of the button
     */
    public MenuQuitToTitleButton(final String text) {
        super(text);
        this.stateObserver = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getAction() {
        return () -> stateObserver.onMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
