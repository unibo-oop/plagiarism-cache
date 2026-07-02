package it.unibo.biscia.events;

import java.util.function.Consumer;

/**
 * a notifier of events to generic observers.
 * @param <T> the listeners of events
 */
public interface GenericEventSubject<T> {

    /**
     * add observer.
     * @param observer the target of notifies
     */
    void attach(T  observer);

    /**
     * remove observer.
     * @param observer the observer to remove from listeners
     */
    void detach(T observer);

    /**
     * perform an action to any listener registered.
     * @param event the action to perform
     */
    void notify(Consumer<T> event);

}
