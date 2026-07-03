package controller.event;

/**
 * Defines a StandardEvent which is a generic type of event.
 */
public class StandardEvent implements Event {

    private final String message;

    /**
     * Constructs a new instance of StandardEvent.
     * 
     * @param m
     *            event's message
     */
    public StandardEvent(final String m) {
        this.message = m;
    }

    /**
     * Get the message of this StandardEvent.
     * 
     * @return the event's message
     */
    public String getMessage() {
        return message;
    }
}
