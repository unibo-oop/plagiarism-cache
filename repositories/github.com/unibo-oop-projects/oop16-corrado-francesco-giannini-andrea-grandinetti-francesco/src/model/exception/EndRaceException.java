package model.exception;
/**
 * Exception for the end of the race.
 */
public class EndRaceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    public EndRaceException() {
        super();
    }
   /**
    * 
    * @param message message
    */
    public EndRaceException(final String message) {
        super(message);
    }
    /**
     * 
     * @param cause cause
     */
    public EndRaceException(final Throwable cause) {
        super(cause);
    }
    /**
     * 
     * @param message message
     * @param cause cause
     */
    public EndRaceException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * 
     * @param message message
     * @param cause cause
     * @param enableSuppression enableSuppression
     * @param writableStackTrace writableStackTrace
     */
    public EndRaceException(final String message, final  Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
