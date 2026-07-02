package jwhale.model.connector;

/**
 * Checked exception, this is thrown when a request fails.
 * 
 */
public class DaemonResponseException extends Exception {

    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = -5543148532007707573L;
    /**
     * Error message.
     */
    private static final String MSG = "Unexpected HTTP status code: ";

    public DaemonResponseException(final String s) {
        super(MSG + s);
    }

}
