package starcatraz.model.game;

import starcatraz.model.cards.CardColor;
import starcatraz.model.cards.CardType;

/**
 * Exception thrown when an invalid card is provided as a method argument.
 */
public class InvalidCardException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * Constructor for InvalidCardException.
     * @param type
     * @param color
     */
    public InvalidCardException(final CardType type, final CardColor color) {
        super();
        this.message = "The card of type " + type.toString()
                     + " and color " + color.toString()
                     + " cannot be used in this situation";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
