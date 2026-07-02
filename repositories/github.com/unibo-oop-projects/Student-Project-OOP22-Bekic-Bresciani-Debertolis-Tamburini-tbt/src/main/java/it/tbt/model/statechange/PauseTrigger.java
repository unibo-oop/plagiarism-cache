package it.tbt.model.statechange;

/**
 * Simple object for triggering the Pause GameState on a StateObserver object.
 */
public class PauseTrigger {

    private final StateObserver stateObserver;

    /**
     * @param stateObserver which needs to be notified.
     */
    public PauseTrigger(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

    /**
     * @param pauseTrigger clone alternative constructor.
     */
    public PauseTrigger(final PauseTrigger pauseTrigger) {
        this.stateObserver = pauseTrigger.stateObserver;
    }

    /**
     * Triggers the Pause GameState.
     */
    public void triggerPause() {
        this.stateObserver.onPause();
    }

}
