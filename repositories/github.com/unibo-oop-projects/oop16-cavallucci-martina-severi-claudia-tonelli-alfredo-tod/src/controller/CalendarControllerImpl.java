package controller;

import java.awt.Component;
import java.util.List;
import java.util.Map;

import view.CalendarView;
import view.CalendarViewImpl;

/**
 * Calendar Controller.
 *
 */
public final class CalendarControllerImpl implements CalendarController {
    private CalendarViewImpl calendarView;
    private static final CalendarController SINGLETON = new CalendarControllerImpl();
    /**
     * update view.
     */
    public void update() {
        this.calendarView = new CalendarViewImpl(this);
        this.calendarView.init();
    }
    /**
     * events before you start application.
     */
    public void beforeEvents() {
        List<String> eventi;
        eventi = MainControllerImpl.manager.currentUser().getCalendar().eventsToBeNotified();
        for (final String mex : eventi) {
            this.calendarView.showMessage(mex, (Component) this.calendarView);
        }
    }

    private CalendarControllerImpl() {
        this.update();
        this.beforeEvents();
    }
    /**
     * 
     * @return SINGLETON
     * singleton
     */
    public static CalendarController getInstance() {
        return SINGLETON;
    }
    /**
     * 
     * @return calendarView
     * calendar view
     */
    public CalendarView getCalendarView() {
        return this.calendarView;
    }
    /**
     * @param day
     * day
     * @param month
     * month
     * @param year
     * year
     */
    public void goToSummary(final int day, final int month, final int year) {
        final EventSummaryControllerImpl eventController = new EventSummaryControllerImpl(day, month, year);
        eventController.update();
    }

    @Override
    public Map<Integer, String> loadEvent(final int anno, final int mese) {
        Map<Integer, String> map;
        final org.joda.time.LocalDate data = org.joda.time.LocalDate.parse(anno + "-" + mese + "-01");
        map = MainControllerImpl.manager.currentUser().getCalendar().monthEventsToString(data);
        return map;
    }
}
