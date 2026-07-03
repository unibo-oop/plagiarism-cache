package it.unibo.coinquify.mansionsmng.model;

import java.time.DayOfWeek;

import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.utils.Time;

/**
 * room mate mansion interface.
 */
public interface Mansion {
    /**
     * 
     * @return mansion start time
     */
    Time getStartTime();

    /**
     * 
     * @return mansion end time
     */
    Time getEndTime();

    /**
     * 
     * @return mansion name
     */
    MansionType getName();

    /**
     * 
     * @return mansion day of week
     */
    DayOfWeek getDayOfWeek();

    /**
     * Text of mansion button.
     * 
     * @param roomMate
     *            of this mansion
     * @return the string rapresentation
     */
    String getButtonText(RoomMate roomMate);
}
