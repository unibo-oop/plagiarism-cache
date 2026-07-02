package aoc.utilities;

/**
 * This interface describes an object capable of observing another object
 * and being notified when a certain event happens.
 */
public interface ObjectObserver {
    
    /**
     * This method is called by outer objects to notify
     * some kind of event to the one that extends this interface.
     * @param notifier
     *            The object that calls the method or causes the event.
     */
    void notify(Object notifier);
}