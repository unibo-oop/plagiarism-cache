package bzzbomber.model;

/**
 * it is a event called when the timer change his value.
 */
public class TimerEvent {
    private final int value;

    /**
     * constructor.
     * 
     * @param v
     *            initial value of the event.
     */
    public TimerEvent(final int v) {
        value = v;
    }

    /**
     * get the value.
     * 
     * @return the number of seconds left.
     */
    public int getValue() {
        return value;
    }
}
