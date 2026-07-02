package ballblast.commons.events;

import java.util.List;

/**
 * 
 * Interface representing an handler of events.
 * 
 * @param <X> The type of event to be handled.
 */

@FunctionalInterface
public interface EventHandler<X> {
    /**
     * Handles an event.
     * 
     * @param event The events to be handled.
     */
    void handleEvent(X event);

    /**
     * Handles all the available events.
     * 
     * @param events The list of events.
     */
    default void handleAll(List<X> events) {
        events.forEach(this::handleEvent);
    }

}
