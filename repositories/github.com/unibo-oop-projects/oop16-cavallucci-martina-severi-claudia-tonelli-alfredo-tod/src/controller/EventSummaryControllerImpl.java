package controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static controller.EventInfo.DESCRIPTION;
import static controller.EventInfo.STARTTIME;
import static controller.EventInfo.PLACE;
import static controller.EventInfo.ENDTIME;

import org.joda.time.LocalTime;

import model.AbstractEvent;
import model.Contact;
import model.ContactImpl;
import model.Event;
import model.EventImpl;
import view.EventSummaryViewImpl;
/**
 * 
 * event summary controller.
 *
 */
public class EventSummaryControllerImpl implements EventSummaryController {
    private EventSummaryViewImpl eventView;
    private final org.joda.time.LocalDate data;
    private Optional<AbstractEvent> selectedEvent;

    EventSummaryControllerImpl(final int day, final int month, final int year) {
        this.data = new org.joda.time.LocalDate(year, month, day);
    }

    @SuppressWarnings("null")
    private List<Contact> stringToList(final String[][] stringa) {
        final List<Contact> list = null;
        if (stringa != null) {
        for (int i = 0; i <= stringa.length; i++) {
            final ContactImpl.Builder b = new ContactImpl.Builder();
            b.name(stringa[i][0]);
            b.surname(stringa[i][1]);
            list.add(b.build());
        }
        }
        return list;
    }
    /**
     * update.
     */
    public void update() {
        final List<AbstractEvent> list = MainControllerImpl.manager.currentUser().getCalendar().dayEventsToList(data);
        final Object[][] eventTable = new Object[list.size()][EventSummaryViewImpl.getContactTableTitles().length];
        for (int i = 0; i < list.size(); i++) {
            final AbstractEvent e = list.get(i);
            eventTable[i][0] = e.getDescription();
            eventTable[i][1] = e.getStartTime();
        }
        this.eventView = new EventSummaryViewImpl(this, data.toString(), MainControllerImpl.manager.currentUser().getAddressBook().getAddressBook(), eventTable);
        this.eventView.init();
    }

    @Override
    public void saveChanges(final Map<EventInfo, String> map, final String[][] list) {
        Event event;
        LocalTime startTime, endTime;
        String timeString, endTimeString;
        endTimeString = map.get(ENDTIME);
        timeString = map.get(STARTTIME);
        endTime = LocalTime.parse(endTimeString);
        startTime = LocalTime.parse(timeString);
        try {
            final EventImpl.Builder b = new EventImpl.Builder();
            if (!map.get(DESCRIPTION).isEmpty()) {
                b.description(map.get(DESCRIPTION));
                b.startTime(startTime);
                b.place(map.get(PLACE));
                b.endTime(endTime);
                if (this.stringToList(list) != null) {
                    b.contacts(this.stringToList(list));
                }
            }
            b.notify(true);
            event = b.build();
            MainControllerImpl.manager.currentUser().getCalendar().addEvent(data, event);
            this.eventView.showMessage("Event successfully added", this.eventView);
            this.eventView.quit();
        } catch (Exception e) {
            this.eventView.showMessage("Error creating event", this.eventView);
        }
    }

    @Override
    public void cancelEvent() {
        this.checkIfSelection();
        this.selectedEvent.ifPresent(g -> {
            MainControllerImpl.manager.currentUser().getCalendar().removeEvent(data, g);
            this.selectedEvent = Optional.empty();
            this.eventView.showMessage("Event successfully removed, please refresh the calendar view", this.eventView);
            this.eventView.quit();
        });
    }
    private void checkIfSelection() {
        if (!this.selectedEvent.isPresent()) {
            this.eventView.showMessage("No event selected", this.eventView);
        }
    }
    /**
     * 
     * @return collect
     * collect
     */
    public List<AbstractEvent> getAllEvents() {
        return MainControllerImpl.manager.currentUser().getCalendar().dayEventsToList(data).stream()
                .sorted((g1, g2) -> g1.getDescription().compareTo(g2.getDescription()))
                .collect(Collectors.toList());
    }
    @Override
    public void selectEvent(final int index) {
        this.selectedEvent = Optional.of(this.getAllEvents().get(index));
    }
}
