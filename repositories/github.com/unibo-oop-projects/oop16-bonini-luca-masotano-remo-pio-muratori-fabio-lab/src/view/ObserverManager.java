package view;

import controller.Observer;
import controller.event.Event;

/**
 * Class used both to comunicate with a collection of
 * {@link controller.Observer} and manage it. Each state should provide it's own
 * observers and notify them when an event is generated.
 */
public interface ObserverManager {

    /**
     * Notify each saved observer that an event occurred.
     * 
     * @param event
     *            the specific ViewEvent occurred
     */
    void notifyAll(Event event);

    /**
     * Adds an Observer to the list of current observers.
     * 
     * @param observer
     *            the specific controller.Observer observer
     */
    void addObserver(Observer observer);

    /**
     * Remove the specified observer from the collection of observers.
     * 
     * @param obs
     *            the specific observer to be removed
     */
    void removeObserver(Observer obs);
}
