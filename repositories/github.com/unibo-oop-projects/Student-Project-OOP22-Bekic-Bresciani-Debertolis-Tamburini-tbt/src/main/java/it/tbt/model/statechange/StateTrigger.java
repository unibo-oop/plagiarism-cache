package it.tbt.model.statechange;

/**
 * Interface for objects who want to change the GameState.
 */
public interface StateTrigger {

    /**
     * This object shall have a reference to a StateObserver in order to notify it for changes of the GameState.
     * @param stateObserver
     */
    void setStateObserver(StateObserver stateObserver);
}
