package it.unibo.javacrush.controller.api;

import java.util.Optional;

import it.unibo.javacrush.common.AppEventType;

/**
 * Interface representing an event.
 */
public interface Event {

    /**
     * Get the type of the event.
     * 
     * @return the type of the event
     */
    AppEventType type();

    /**
     * Get the ID associated with the event, if any.
     * 
     * @return the ID associated
     */
    Optional<Integer> id();
}
