package controller;

import java.util.Map;

/**
 * 
 * event summary controller.
 *
 */
public interface EventSummaryController {
    /**
     * save changes.
     * save
     * @param map
     * map
     * @param list
     * list
     */
    void saveChanges(Map<EventInfo, String> map, String[][] list);
    /**
     * 
     */
    void cancelEvent();
    /**
     * 
     * @param index
     * index
     */
    void selectEvent(int index);
}
