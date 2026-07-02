package it.unibo.javacrush.common;

import java.util.Optional;

import it.unibo.javacrush.controller.api.Event;

/**
 * Record representing a game event, implementing the {@link Event} interface.
 * 
 * @param type the type of the event
 * @param id an optional integer representing the id of the level to start
 */
public record GameEvent(
    AppEventType type,
    Optional<Integer> id) implements Event {

}
