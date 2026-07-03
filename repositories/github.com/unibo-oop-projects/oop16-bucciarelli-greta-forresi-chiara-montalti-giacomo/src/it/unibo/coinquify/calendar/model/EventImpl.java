package it.unibo.coinquify.calendar.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.Time;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * Event class.
 */
public class EventImpl implements Event, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 930145402358710471L;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private String title;
    private String description;
    private String location;

    /**
     * Constructor of event.
     * 
     * @param startDate
     *            of event
     * @param endDate
     *            of event
     * @param startTime
     *            of event
     * @param endTime
     *            of event
     * @param title
     *            of event
     * @param description
     *            of event
     * @param location
     *            of event
     */
    public EventImpl(final Date startDate, final Date endDate, final Time startTime, final Time endTime,
            final String title, final String description, final String location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
        this.startTime = startTime;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public Time getStartTime() {
        return this.startTime;
    }

    @Override
    public Time getEndTime() {
        return this.endTime;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setStartTime(final Time startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(final Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public void setLocation(final String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Data inzio = " + DateFormat.getDateInstance(DateFormat.LONG).format(this.getStartDate())
                + "Data fine = " + DateFormat.getDateInstance(DateFormat.LONG).format(this.getEndDate()) + "\nTitolo = "
                + this.getTitle() + " Luogo = " + this.getLocation() + "\nDescrizione = " + this.getDescription()
                + "\nOra d'inizio = " + this.getStartTime() + " Ora di fine  = " + this.getEndTime();
    }

    @Override
    public boolean isActive() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Messages.getMessages().getCurrentLocale());
        final Date d = new Date();
        final java.util.Calendar calendar = GregorianCalendar.getInstance();
        final Time actualTime = new TimeImpl(calendar.get(java.util.Calendar.HOUR_OF_DAY),
                calendar.get(java.util.Calendar.MINUTE));
        calendar.setTime(d); // assigns calendar to given date
        return this.getEndDate().after(d) || (dateFormat.format(this.getEndDate()).equals(dateFormat.format(d))
                        && this.getEndTime().after(actualTime));
    }
}
