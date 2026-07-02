package it.unibo.biscia.events;

/**
 * for notify of player actions.
 */
public interface ActionSubject {

    /**
     * add listener.
     * @param observer subject to notify
     */
    void attachActionObserver(ActionObserver observer);

    /**
     * remove listener.
     * @param observer subject to notify
     */
    void detachActionObserver(ActionObserver observer);

}
