package it.unibo.biscia.events;

/**
 * for notify of general modify to a state of game.
 *
 */
public interface StateSubject {

    /**
     * add listener.
     * 
     * @param observer subject to notify
     */
    void attachStateObserver(StateObserver observer);

    /**
     * remove listener.
     * 
     * @param observer subject to notify
     */
    void detachStateObserver(StateObserver observer);
}
