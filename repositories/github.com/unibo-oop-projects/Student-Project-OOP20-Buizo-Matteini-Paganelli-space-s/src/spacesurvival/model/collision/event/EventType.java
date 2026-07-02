package spacesurvival.model.collision.event;

import java.util.Optional;
import java.util.stream.Stream;
import spacesurvival.model.worldevent.WorldEvent;

/**
 * Event Type.
 */
public enum EventType {

    /**
     * Represent the HitBorderEvent.
     */
    HIT_BORDER,
    /**
     * Represent the HitBulletEvent.
     */
    HIT_BULLET,
    /**
     * Represent the HitMainObjectEvent.
     */
    HIT_MAIN_OBJECT,
    /**
     * Represent the HitTakeableEvent.
     */
    HIT_TAKEABLE_OBJECT,
    /**
     * Represent the Dead Event.
     */
    DEAD_EVENT;

    /**
     * Return the corresponding EventType for the WorldEvent passed.
     * 
     * @param event the event to check
     * @return an optional of EventType if the passed WorldEvent exists or an empty optional
     */
    public static Optional<EventType> getEventFromHit(final WorldEvent event) {
        return Stream.of(EventType.values()).filter(ev -> ev.equals(event.getType())).findFirst();
    }
}

























