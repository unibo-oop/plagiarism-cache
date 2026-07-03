package model;

import java.io.Serializable;

import org.joda.time.LocalTime;

/**
 * This interface represents a event of the user calendar.
 */
public interface AbstractEvent extends Serializable {

    /**
     * @return event's description
     */
    String getDescription();

    /**
     * @return the start time of the appointment
     */
    LocalTime getStartTime();

    /**
     * @return if the event should be notified
     */
    boolean isNotify();

    /**
     * Sets the event notified (notify = false).
     */
    void setNotified();
}
