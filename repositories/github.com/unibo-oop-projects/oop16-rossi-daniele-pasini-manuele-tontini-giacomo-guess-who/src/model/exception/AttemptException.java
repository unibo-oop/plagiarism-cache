package model.exception;

/**
 * Exception thrown when a player tries to guess the opponent's character directly but has no more attempts.
 */
public class AttemptException extends Exception {

    /***/
    private static final long serialVersionUID = 7242048537449091356L;

    /***/
    public AttemptException() {
        super("Tentativi per indovinare terminati");
    }
}
