package model.world;

public class OccupedSquareException extends RuntimeException {

    /**
     * utomatically generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * construct a new OccupedSquareException with a message error
     * @param message 
     *                  the error message
     */
    public OccupedSquareException(final String message) {
        super(message);
    }
}
