package exceptions;

/**
 * It is the exception that is thrown when the owner tries to add as a new film a movie 
 * already in the movie list of the cinema.
 *
 */
public class NewFilmException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1815921036770058781L;

    @Override
    public String getMessage() {
        return "This film already exist in the movie list.";
    }


}
