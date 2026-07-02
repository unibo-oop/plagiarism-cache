package model.world;

public class CantMoveinDirectionException extends RuntimeException {

    /**
     * automtically generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * construct a new CantMoveinDirectionException with a given error message
     * @param message
     *                  the error message
     */
    public CantMoveinDirectionException(final String message) {
        super(message);
    }
}
