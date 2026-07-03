package controller;

import controller.event.Event;

/**
 * Handles the events.
 */
public interface Observer {

    /**
     * Notifies the observer with the event given.
     * 
     * @param e
     *            event
     * @param <E>
     *            event type
     */
    <E extends Event> void onNotify(E e);

}
