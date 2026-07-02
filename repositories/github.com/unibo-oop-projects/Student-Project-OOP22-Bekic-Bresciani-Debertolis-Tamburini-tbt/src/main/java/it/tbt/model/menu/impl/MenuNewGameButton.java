package it.tbt.model.menu.impl;

import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

/**
 * The {@code MenuNewGameButton} class represents a menu button for starting a
 * new game.
 * It extends the {@link AbstractMenuButton} class and implements the
 * {@link StateTrigger} interface.
 */
public class MenuNewGameButton extends AbstractMenuButton implements StateTrigger {
    private StateObserver stateObserver;

    /**
     * Creates a new instance of {@code MenuNewGameButton} with the specified text.
     *
     * @param text the text of the button
     */
    public MenuNewGameButton(final String text) {
        super(text);
        stateObserver = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getAction() {

        return () -> stateObserver.onExplore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
