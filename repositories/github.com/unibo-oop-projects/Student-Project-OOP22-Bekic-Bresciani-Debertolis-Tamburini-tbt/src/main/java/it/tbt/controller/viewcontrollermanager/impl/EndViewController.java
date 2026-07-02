package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.EndState;

/**
 * The {@code EndViewController} class represents the view controller for the end state.
 * It handles user input and triggers actions associated with the end state.
 */
public class EndViewController extends AbstractViewController {

    private final EndState endState;

    /**
     * Constructs a new {@code EndViewController} with the specified end state.
     *
     * @param endState the end state
     * @throws IllegalArgumentException if the endState is null
     */
    public EndViewController(final EndState endState) {
        if (endState == null) {
            throw new IllegalArgumentException("EndState cannot be null");
        }
        this.endState = endState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        endState.triggerExit();
    }

}

