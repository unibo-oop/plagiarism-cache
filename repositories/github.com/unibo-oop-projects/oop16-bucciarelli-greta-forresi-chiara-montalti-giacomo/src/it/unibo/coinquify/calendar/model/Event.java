package it.unibo.coinquify.calendar.model;

import java.util.Date;

import it.unibo.coinquify.utils.Time;

/**
 * Event interface.
 */
public interface Event {
    /**
     * 
     * @return the start date
     */
    Date getStartDate();

    /**
     * 
     * @return the end date
     */
    Date getEndDate();

    /**
     * 
     * @return the start time.
     */
    Time getStartTime();

    /**
     * 
     * @return the end time.
     */
    Time getEndTime();

    /**
     * 
     * @return title of event
     */
    String getTitle();

    /**
     * 
     * @return the event location
     */
    String getLocation();

    /**
     * 
     * @return event description
     */
    String getDescription();

    /**
     * Modify event start date.
     * 
     * @param startDate
     *            to set
     */
    void setStartDate(Date startDate);

    /**
     * Modify event end date.
     * 
     * @param endDate
     *            to set
     */
    void setEndDate(Date endDate);

    /**
     * Modify event start time.
     * 
     * @param startTime
     *            to set
     */
    void setStartTime(Time startTime);

    /**
     * Modify event end time.
     * 
     * @param endTime
     *            to set
     */
    void setEndTime(Time endTime);

    /**
     * Modify event title.
     * 
     * @param title
     *            to set
     */
    void setTitle(String title);

    /**
     * Modify event description.
     * 
     * @param description
     *            to set
     */
    void setDescription(String description);

    /**
     * Modify event location.
     * 
     * @param location
     *            to set
     */
    void setLocation(String location);

    /**
     * 
     * @return true if event isn't passed
     */
    boolean isActive();

}
