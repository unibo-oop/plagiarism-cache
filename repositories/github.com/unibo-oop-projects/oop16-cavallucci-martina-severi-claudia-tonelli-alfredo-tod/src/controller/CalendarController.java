package controller;

import java.util.Map;

import view.CalendarView;

/**
 * 
 * calendar controller interface.
 *
 */
public interface CalendarController {
    /**
     * 
     * @return calendarView
     * calendar view
     */
    CalendarView getCalendarView();
    /**
     * load event.
     * @param mese
     * mese
     * @param anno
     * anno
     * @return map
     * map
     */
    Map<Integer, String> loadEvent(int anno, int mese);
    /**
     * @param giorno
     * giorno
     * @param mese
     * mese
     * @param anno
     * anno
     */
    void goToSummary(int giorno, int mese, int anno);
}
