package it.unibo.coinquify.calendar.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.unibo.coinquify.calendar.model.Event;

/**
 * Calendar controller interface.
 */
public interface CalendarController {

    /**
     * Controller add event.
     * 
     * @param event
     *            to control
     * @throws IllegalArgumentException
     *             if the arguments are incorrects.
     */
    void addEvent(Event event) throws IllegalArgumentException;

    /**
     * 
     * @param date
     *            if there is a date display only the events for this date
     * @return list of events
     */
    List<Event> getEvents(Optional<Date> date);

    /**
     * 
     * @throws IOException
     *             if there're problem with files
     */
    void saveAll() throws IOException;

    /**
     * Remove an event.
     * 
     * @param e
     *            event
     */
    void remove(Event e);

    /**
     * Update an event.
     * 
     * @param event
     *            to update.
     * @param eventToControl
     *            new event to control
     * @throws IllegalArgumentException
     *             if the argument aren't ok.
     */
    void update(Event event, Event eventToControl) throws IllegalArgumentException;

    /**
     * Deletes no active events.
     */
    void deleteOldEvents();

    /**
     * 
     * @return a list of current event, now in progress
     */
    List<Event> getCurrentEvents();

}