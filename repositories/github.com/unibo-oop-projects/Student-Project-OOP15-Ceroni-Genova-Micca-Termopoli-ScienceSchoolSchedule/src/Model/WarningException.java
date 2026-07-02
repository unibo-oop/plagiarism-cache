package Model;

/**
 * This class implements the warning belonging to the exceptions
 * 
 * @author Francesco Ceroni
 * 
 */

public class WarningException extends Exception {

    /**
    * 
    */
    private static final long serialVersionUID = 4512453331501755864L;
    private final String message;
    
    /**
     * Builds a new WarningException
     * @param m
     *          the message of this exception
     */

    public WarningException(final String m) {
        super();
        this.message = m;
    }
    
    /**
     * @return the message of this exception
     */

    public final String getMessage() {
        return this.message;
    }
}
