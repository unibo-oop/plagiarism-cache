package model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import com.google.common.collect.Multimap;

/**
 * This interface represents the calendar of the user.
 */
public interface Calendar extends Serializable {

    /**
     * Add an event in the calendar.
     * 
     * @param date
     *            the event is held
     * @param event
     *            to be inserted into the calendar
     * @throws IllegalArgumentException
     *             if the Event is already registered in this date
     */
    void addEvent(LocalDate date, AbstractEvent event) throws IllegalArgumentException;

    /**
     * Populate the calendar with the birthday events of a contact.
     * 
     * @param contact
     *            of the address book
     * @throws IllegalArgumentException
     *             if cannot insert a birthday
     */
    void addBirthday(Contact contact) throws IllegalArgumentException;

    /**
     * Remove from calendar the birthday events of a contact.
     * 
     * @param contact
     *            of the address book
     * @throws IllegalArgumentException
     *             if cannot delete a birthday
     */
    void removeBirthday(Contact contact) throws IllegalArgumentException;

    /**
     * Remove an event in the calendar.
     * 
     * @param date
     *            the event is held
     * @param event
     *            to be removed from the calendar
     * @throws IllegalArgumentException
     *             if the event is not registered in input date
     */
    void removeEvent(LocalDate date, AbstractEvent event) throws IllegalArgumentException;

    /**
     * Get a Multimap of the user's calendar.
     * 
     * @return user's calendar
     */
    Multimap<LocalDate, AbstractEvent> getCalendar();

    /**
     * Get a string with all the events sorted by time of a certain day.
     * 
     * @param date
     *            date that contains the desired month
     * @return a map with all events, day by day, of the month sorted by time
     */
    Map<Integer, String> monthEventsToString(LocalDate date);

    /**
     * Get a list with all the events sorted by time of a certain day.
     * 
     * @param date
     *            of the day
     * @return list of events of the day
     */
    List<AbstractEvent> dayEventsToList(LocalDate date);

    /**
     * Set the last user logout date.
     */
    void setLastUserLogout();

    /**
     * @return a list with all the events that that must be notified
     */
    List<String> eventsToBeNotified();
}
