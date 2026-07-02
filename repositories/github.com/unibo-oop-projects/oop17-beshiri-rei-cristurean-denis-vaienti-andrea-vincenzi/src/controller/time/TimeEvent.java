package controller.time;

/**
 * 
 * Class that represent a timer event.
 *
 */
public class TimeEvent {

    private final Time value;

    /**
     * Constructor for this class.
     * @param time time when the event occur.
     */
    public TimeEvent(final Time time) {
        value = time;
    }

    /**
     * Method used to return time of the event.
     * @return the time registered when the event occur.
     */
    public Time getTime() {
        return value;
    }
}
