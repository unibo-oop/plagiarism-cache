package it.unibo.coinquify.calendar.model;

import java.util.Date;
import java.util.List;

/**
 * Shared calendar.
 */
public interface Calendar {
    /**
     * add event to calendar.
     * 
     * @param event
     *            to add
     */
    void addEvent(Event event);

    /**
     * 
     * @return all events
     */
    List<Event> getEvents();

    /**
     * 
     * @param startDate
     *            to see events
     * @return list of events starting this day
     */
    List<Event> getEventsStartingInADay(Date startDate);

    /**
     * Remove an event.
     * 
     * @param e
     *            event
     */
    void remove(Event e);

    /**
     * 
     * @param event
     *            to update
     * @param eventChanged
     *            new event informations
     */
    void updateEvent(Event event, Event eventChanged);

    /**
     * delete all no active events..
     */
    void deleteOldEvents();

    /**
     * 
     * @return a list of current event, now in progress
     */
    List<Event> getCurrentEvents();
}
