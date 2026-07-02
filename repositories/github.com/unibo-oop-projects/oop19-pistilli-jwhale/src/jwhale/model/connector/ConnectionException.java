package jwhale.model.connector;

/**
 * Checked exception, this is thrown when destination is unreachable or 
 * connection is refused. It wraps IOException and InterruptedException.
 */
public class ConnectionException extends Exception {
    /**
     * Generated serial version.
     */
    private static final long serialVersionUID = 3993217114810220512L;
    /**
     * Error message.
     */
    private static final String MSG = "Connection refused or destination unreachable";

    public ConnectionException() {
        super(MSG);
    }

}
