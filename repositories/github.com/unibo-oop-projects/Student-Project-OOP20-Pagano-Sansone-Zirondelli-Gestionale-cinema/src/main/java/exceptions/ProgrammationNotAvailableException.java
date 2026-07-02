package exceptions;

public class ProgrammationNotAvailableException extends Exception {

    /**
     * Constructs a DateNotAvailableException with "Schedule this film not available
     * , try to change start time od date as its error message.
     */
    public ProgrammationNotAvailableException() {
        super("Schedule this film  not available , try to change start time od date ");
    }

    /**
     * Constructs a DateNotAvailableException with the specified message.
     * 
     * @param message the specified error message.
     */
    public ProgrammationNotAvailableException(final String message) {
        super(message);
    }

}
