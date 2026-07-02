package controller.timer;

/**
 * 
 * This class represent an Exception 
 * that indicate that the available time is over.
 *
 */
public class OverTimeException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 2634065795379254989L;

   /**
    * Simple Constructor of OverTimerException. 
    * 
    */
    public OverTimeException() {
        super("The available time is over.");
    }
}
