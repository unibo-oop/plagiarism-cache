package ballblast.model.events;

import java.util.List;

import ballblast.commons.events.EventType;

/**
 * Stores a list of {@link EventType}.
 */
public interface GameEventManager {
    /**
     * Adds game event to the list.
     * 
     * @param event the event to be added.
     */
    void addGameEvent(EventType event);

    /**
     * Gets the stored game events and empties the list.
     * 
     * @return the stored list.
     */
    List<EventType> getGameEvents();
}
