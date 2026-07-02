package spacesurvival.model.worldevent;

import spacesurvival.model.World;
import spacesurvival.model.collision.event.EventType;

/**
 * 
 * Interface for every world event.
 *
 */
public interface WorldEvent {

    /**
     * Return the current event type.
     * 
     * @return an EventType for this event
     */
    EventType getType();

    /**
     * Manage the event's consequences.
     * @param world
     */
    void manage(World world);
}
