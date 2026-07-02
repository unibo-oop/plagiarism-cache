package it.tbt.controller.modelmanager.transitionmanager.api;

import it.tbt.controller.modelmanager.ModelState;
import it.tbt.model.GameState;
import it.tbt.model.statechange.StateObserver;

/**
 * GameState Transition Manager, it is a StateObserver because he wants
 * to be alerted on every State change.
 */
public interface TransitionManager extends StateObserver {

    /**
     * Operations to set the TransitionManager working.
     */
    void init();
    /**
     * @return The GameState if the TransitionManager has been initialized.
     */
    GameState getState();
    /**
     * @return the current ModelState if the TransitionManager has been initialized.
     */
    ModelState getCurrentModelState();
    /**
     * @return true if a State change has been observed
     * , and it's the first time this method as been called since the State change has been Observed.
     * Returns false otherwise.
     */
    Boolean hasStateChanged();
}
