package model.exception;

/**
 * Thrown when human player lie to a question posed by the AI player.
 */
public class UserLiedException extends Exception {

    /***/
    private static final long serialVersionUID = 1597238098820152648L;

    /**
     * @param msg the text of the question to which the user has lied
     */
    public UserLiedException(final String msg) {
        super("[L'utente ha mentito alla domanda: '" + msg + "']");
    }
}
