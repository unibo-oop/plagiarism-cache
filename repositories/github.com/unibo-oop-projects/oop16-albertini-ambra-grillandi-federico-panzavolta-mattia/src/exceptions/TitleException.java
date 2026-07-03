package exceptions;

/**
 * It is the exception that is thrown when the the film researched 
 * with that title is not in the film list.
 */
public class TitleException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7808946426532369386L;

    @Override
    public String getMessage() {
        return "This film does not exist in the movie list.";
    }
}
