package zengine.interfaces;

/**
 * This interface represents the Zengine component that receives and handles
 * messages. The messages can be info messages, warnings and errors. Info
 * messages are printed to stout by default and are debug informations. They can
 * be silenced. Warnings are the same as infos, but they are printed to sderr
 * and they represent an event that occurred when something wrong happened that
 * still does not interrupt the application. Warnings can be silenced. Errors
 * are printed to stderr and they force the interruption of the application,
 * they cannot be silenced.
 */
public interface GameFunctionsLogger {

    // functions to monitor if somethings is happening

    /**
     * turns on or off the messages of the logger. if it's set to off, every
     * message that will be sent to the logger from this point on will be
     * ignored.
     * 
     * @param yesOrNo
     *            true for on, false for off
     */
    void loggerEnableMessages(boolean yesOrNo);

    /**
     * turns on or off the warnings of the logger. if it's set to off, every
     * warning that will be sent to the logger from this point on will be
     * ignored.
     * 
     * @param yesOrNo
     *            true for on, false for off
     */
    void loggerEnableWarnings(boolean yesOrNo);

    /**
     * sends the given message to the logger. It will be printed to stdout by
     * default
     * 
     * @param message
     *            message to be sent
     */
    void loggerMessage(String message);

    /**
     * sends the given warning to the logger. It's the same as a message but
     * will be printed to stderr by default
     * 
     * @param warning
     *            warning to be sent
     */
    void loggerWarning(String warning);

    /**
     * sends the given error to the logger. It will be printed to stderr by
     * default. Also, the execution of zengine will be interrupted
     * 
     * @param error
     *            error message to be sent
     */
    void loggerError(String error);
}
