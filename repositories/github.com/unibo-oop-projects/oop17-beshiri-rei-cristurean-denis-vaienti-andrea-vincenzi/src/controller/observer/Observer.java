package controller.observer;

import controller.event.Event;

/**
 * Observer interface that wants to be informed of changes in observable objects.
 */
public interface Observer {

    /**
     * 
     * @param event event that happened.
     * @param <E> type of event that happened.
     */
    <E extends Event> void notifyEvent(E event);
}
