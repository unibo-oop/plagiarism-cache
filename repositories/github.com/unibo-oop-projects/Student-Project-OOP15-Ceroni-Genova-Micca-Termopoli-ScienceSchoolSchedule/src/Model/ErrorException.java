package Model;

/**
 * This class implements the errors belonging to the exceptions
 * 
 * @author Francesco Ceroni
 * 
 */

public class ErrorException extends Exception {

    /**
    * 
    */
    private static final long serialVersionUID = 1334396525055957672L;
    private final String message;
    
    /**
     * Builds a new ErrorException
     * @param m
     *          the message of this exception
     */

    public ErrorException(final String m) {
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
