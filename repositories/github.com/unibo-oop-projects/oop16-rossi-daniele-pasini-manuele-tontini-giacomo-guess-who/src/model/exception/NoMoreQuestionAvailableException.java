package model.exception;

/**
 * Exception thrown when questions left to a player are requested but they are no longer available(finish the game).
 */
public class NoMoreQuestionAvailableException extends Exception {

    /***/
    private static final long serialVersionUID = -1165482158183366097L;

    /**
     * @param msg utente/computer
     */
    public NoMoreQuestionAvailableException(final String msg) {
        super("[" + msg + " non ha più domande disponibili ]");
    }
}
