package it.unibo.coinquify.calendar.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.Time;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * Implementation of calendar.
 */
public class CalendarImpl implements Calendar {
    private final List<Event> events;

    /**
     * empty calendar constructor.
     */
    public CalendarImpl() {
        this.events = new ArrayList<>();
    }

    /**
     * calendar with some events.
     * 
     * @param events
     *            to add
     */
    public CalendarImpl(final List<Event> events) {
        this.events = events;
    }

    @Override
    public void addEvent(final Event event) {
        this.events.add(event);
    }

    @Override
    public List<Event> getEvents() {
        return Collections
                .unmodifiableList(this.events.stream().sorted(new EventComparator()).collect(Collectors.toList()));
    }

    @Override
    public List<Event> getEventsStartingInADay(final Date startDate) {
        return this.events.stream().filter(d -> d.getStartDate().equals(startDate)).sorted(new EventComparator())
                .collect(Collectors.toList());
    }

    @Override
    public void remove(final Event e) {
        this.events.remove(e);
    }

    @Override
    public void updateEvent(final Event event, final Event eventImpl) {
        this.events.remove(event);
        this.events.add(eventImpl);
    }

    @Override
    public void deleteOldEvents() {
        // iterator for not have ConcurrentModificationException
        for (final Iterator<Event> iterator = this.events.iterator(); iterator.hasNext();) {
            final Event e = iterator.next();
            if (!e.isActive()) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Event> getCurrentEvents() {
        final List<Event> eventInProgress = new ArrayList<>();

        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Messages.getMessages().getCurrentLocale());
        final Date d = new Date();
        final java.util.Calendar calendar = GregorianCalendar.getInstance();
        final Time actualTime = new TimeImpl(calendar.get(java.util.Calendar.HOUR_OF_DAY),
                calendar.get(java.util.Calendar.MINUTE));
        calendar.setTime(d); // assigns calendar to given date

        for (final Event e : this.events) {
            if (e.getStartDate().before(d)) {
                // control if date is today, date time don't control that
                // for it's second, the start date is before today
                if (dateFormat.format(e.getStartDate()).equals(dateFormat.format(d))) {
                    if (!e.getStartTime().after(actualTime) && (e.getEndDate().after(d)
                            || dateFormat.format(e.getEndDate()).equals(dateFormat.format(d))
                                    && e.getEndTime().after(actualTime))) {
                        eventInProgress.add(e);
                    }
                } else if (e.getEndDate().after(d) || dateFormat.format(e.getEndDate()).equals(dateFormat.format(d))
                        && e.getEndTime().after(actualTime)) {
                    eventInProgress.add(e);
                }
            }
        }
        return eventInProgress;
    }

}
