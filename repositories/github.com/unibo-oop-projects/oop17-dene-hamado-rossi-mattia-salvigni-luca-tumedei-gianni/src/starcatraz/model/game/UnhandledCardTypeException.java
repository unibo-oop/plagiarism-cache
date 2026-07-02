package starcatraz.model.game;

import starcatraz.model.cards.CardType;

/**
 * Exception thrown when a Card of an unhandled type is provided as a method argument.
 */
public class UnhandledCardTypeException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * Constructor for UnhandledCardTypeException.
     * @param type
     */
    public UnhandledCardTypeException(final CardType type) {
        super();
        this.message = "The card type " + type.toString() + " is not handled";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
