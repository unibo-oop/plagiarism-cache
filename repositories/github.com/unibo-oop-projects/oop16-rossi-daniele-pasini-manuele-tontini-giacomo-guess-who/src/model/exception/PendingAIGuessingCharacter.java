package model.exception;

/**
 * Thrown when AI player attempts to guess instead of asking a question.
 */
public class PendingAIGuessingCharacter extends Exception {

    /***/
    private static final long serialVersionUID = -1872998197796264040L;

    /***/
    public PendingAIGuessingCharacter() {
        super("AI vuole provare ad indovinare");
    }
}
