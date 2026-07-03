package it.unibo.coinquify.calendar.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import it.unibo.coinquify.calendar.model.Calendar;
import it.unibo.coinquify.calendar.model.CalendarImpl;
import it.unibo.coinquify.calendar.model.Event;
import it.unibo.coinquify.file.EventFile;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.Time;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * calendar controller.
 */
public class CalendarControllerImpl implements CalendarController {
    private final Calendar calendar;

    /**
     * create a new calendar controller.
     */
    public CalendarControllerImpl() {
        if (EventFile.readEvents().isEmpty()) {
            this.calendar = new CalendarImpl();
        } else {
            this.calendar = new CalendarImpl(EventFile.readEvents());
        }
    }

    @Override
    public void addEvent(final Event eventToAdd) throws IllegalArgumentException {
        controlFields(eventToAdd);
        this.calendar.addEvent(eventToAdd);
    }

    private void controlFields(final Event eventToAdd) throws IllegalArgumentException {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Messages.getMessages().getCurrentLocale());
        final Date d = new Date();
        final java.util.Calendar calendar = GregorianCalendar.getInstance();
        final Time actualTime = new TimeImpl(calendar.get(java.util.Calendar.HOUR_OF_DAY),
                calendar.get(java.util.Calendar.MINUTE));
        calendar.setTime(d); // assigns calendar to given date
        if (eventToAdd.getTitle().isEmpty() || eventToAdd.getStartDate().after(eventToAdd.getEndDate())
                || (!dateFormat.format(eventToAdd.getStartDate()).equals(dateFormat.format(d))
                        && eventToAdd.getStartDate().before(d))
                || (dateFormat.format(eventToAdd.getStartDate()).equals(dateFormat.format(d))
                        && eventToAdd.getStartTime().before(actualTime))
                || (eventToAdd.getStartDate().equals(eventToAdd.getEndDate())
                        && !eventToAdd.getStartTime().before(eventToAdd.getEndTime()))) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Event> getEvents(final Optional<Date> date) {
        if (date.isPresent()) {
            return this.calendar.getEventsStartingInADay(date.get());
        }
        return this.calendar.getEvents();
    }

    @Override
    public void saveAll() throws IOException {
        EventFile.writeEvents(this.calendar.getEvents());
    }

    @Override
    public void remove(final Event e) {
        this.calendar.remove(e);
    }

    @Override
    public void update(final Event event, final Event eventToControl) throws IllegalArgumentException {
        // may update only some args.
        controlFields(eventToControl);
        this.calendar.updateEvent(event, eventToControl);
    }

    @Override
    public void deleteOldEvents() {
        this.calendar.deleteOldEvents();
    }

    @Override
    public List<Event> getCurrentEvents() {
        return this.calendar.getCurrentEvents();
    }
}
