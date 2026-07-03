package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * This interface represents the calendar of the user.
 */
public class CalendarImpl implements Calendar {

    private static final long serialVersionUID = 5209829497017685721L;
    private final Multimap<LocalDate, AbstractEvent> calendar;
    private LocalDate lastUserLogout = LocalDate.parse("2000-01-01");
    private static final int MAXCALENDARYEAR = 2200;

    /**
     * Builds a Calendar.
     */
    public CalendarImpl() {
        this.calendar = HashMultimap.create();
    }

    @Override
    public void setLastUserLogout() {
        this.lastUserLogout = LocalDate.now();
    }

    @Override
    public void addEvent(final LocalDate date, final AbstractEvent event) throws IllegalArgumentException {
        Objects.requireNonNull(event);
        if (!calendar.put(date, event)) {
            throw new IllegalArgumentException("Event already registered in this date");
        }
    }

    @Override
    public void addBirthday(final Contact contact) throws IllegalArgumentException {
        if (contact.getDateOfBirthValue().isPresent()) {
            Objects.requireNonNull(contact);
            final Birthday localBirthday = new BirthdayImpl(true, "It's " + contact.getName() + "'s Birthday", contact,
                    new LocalTime(12, 0, 0));
            LocalDate start = contact.getDateOfBirthValue().get();
            while (start.getYear() < MAXCALENDARYEAR) {
                try {
                    this.addEvent(start, localBirthday);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Cannot add birthdays", e);
                }
                start = start.plusYears(1);
            }
        }
    }

    @Override
    public void removeBirthday(final Contact contact) throws IllegalArgumentException {
        if (contact.getDateOfBirthValue().isPresent()) {
            Objects.requireNonNull(contact);
            final Birthday localBirthday = new BirthdayImpl(true, "It's " + contact.getName() + "'s Birthday", contact,
                    new LocalTime(12, 0, 0));
            LocalDate start = contact.getDateOfBirthValue().get();
            while (start.getYear() < MAXCALENDARYEAR) {
                try {
                    this.removeEvent(start, localBirthday);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Cannot remove birthdays", e);
                }
                start = start.plusYears(1);
            }
        }
    }

    @Override
    public void removeEvent(final LocalDate date, final AbstractEvent event) throws IllegalArgumentException {
        if (!calendar.remove(date, event)) {
            throw new IllegalArgumentException("Event not registered in this date");
        }
    }

    @Override
    public Multimap<LocalDate, AbstractEvent> getCalendar() {
        return Multimaps.unmodifiableMultimap(calendar);
    }

    private String dayEventsToString(final LocalDate date) {
        return this.calendar.get(date).stream().sorted((p1, p2) -> p2.getStartTime().compareTo(p1.getStartTime()))
                .collect(Collectors.toList()).toString().replace("[", "").replace("]", "").replace(", ", "\n");
    }

    @Override
    public Map<Integer, String> monthEventsToString(final LocalDate date) {
        final Map<Integer, String> res = new HashMap<Integer, String>();
        LocalDate local = date;
        while (local.getMonthOfYear() == date.getMonthOfYear()) {
            final String events = this.dayEventsToString(local);
            if (!events.isEmpty()) {
                res.put(local.getDayOfMonth(), events);
            } else {
                res.put(local.getDayOfMonth(), "");
            }
            local = local.plusDays(1);
        }
        return res;
    }

    @Override
    public List<AbstractEvent> dayEventsToList(final LocalDate date) {
        return this.calendar.get(date).stream().sorted((p1, p2) -> p2.getStartTime().compareTo(p1.getStartTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> eventsToBeNotified() {
        final LocalDate start = this.lastUserLogout.minusDays(1);
        final LocalDate finish = LocalDate.now().plusDays(2);
        return calendar.entries().stream().filter(p -> p.getKey().isAfter(start))
                .filter(p -> p.getKey().isBefore(finish)).filter(p -> p.getValue().isNotify())
                .peek(p -> p.getValue().setNotified())
                .map(map -> "the " + map.getKey() + " at " + map.getValue().toString()).collect(Collectors.toList());
    }
}
