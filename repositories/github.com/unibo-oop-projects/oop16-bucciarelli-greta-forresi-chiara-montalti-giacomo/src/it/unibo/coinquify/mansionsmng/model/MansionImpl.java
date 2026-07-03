package it.unibo.coinquify.mansionsmng.model;

import java.io.Serializable;
import java.time.DayOfWeek;

import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.utils.Time;

/**
 * mansion implementation.
 */
public class MansionImpl implements Mansion, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Time startTime;
    private final Time endTime;
    private final MansionType name;
    private final DayOfWeek dayOfWeek;

    /**
     * 
     * @param name
     *            of mansion
     * @param startTime
     *            of mansion
     * @param endTime
     *            of mansion
     * @param dayOfWeek
     *            of mansion
     */
    public MansionImpl(final MansionType name, final Time startTime, final Time endTime, final DayOfWeek dayOfWeek) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Time getStartTime() {
        return startTime;
    }

    @Override
    public Time getEndTime() {
        return endTime;
    }

    @Override
    public MansionType getName() {
        return name;
    }

    @Override
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return "Attività : " + this.getName() + " " + this.getDayOfWeek() + " dalle " + this.getStartTime() + " alle "
                + this.getEndTime();
    }

    @Override
    public String getButtonText(final RoomMate roomMate) {
        return "<html>" + roomMate.getName() + " " + roomMate.getSurname() + "<br />" + this.getName() + "</html>";
    }
}
