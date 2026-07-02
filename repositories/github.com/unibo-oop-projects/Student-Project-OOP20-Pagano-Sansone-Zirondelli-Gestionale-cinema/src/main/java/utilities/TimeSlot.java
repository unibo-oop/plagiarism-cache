package utilities;

import java.time.LocalTime;
/** 
 *  Time slot indicates a start-end time.
 *   * */
public interface TimeSlot {
    /** 
     * Get start time.
     * @return LocalTime
     * */
    LocalTime getStartTime();
    /** 
     * Get end time.
     * @return LocalTime
     * */
    LocalTime getEndTime();
}
