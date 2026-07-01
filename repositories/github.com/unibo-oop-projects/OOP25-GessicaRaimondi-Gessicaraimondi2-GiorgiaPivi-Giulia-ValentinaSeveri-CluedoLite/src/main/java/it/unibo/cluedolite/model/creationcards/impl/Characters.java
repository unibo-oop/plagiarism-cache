package it.unibo.cluedolite.model.creationcards.impl;

/**
 * Represents a character card in the game.
 *
 * <p>This class is designed to be extended. Subclasses may override {@link #getType()}
 * only if they intend to change the card category returned by this method.
 * All subclasses should preserve the immutability of the card name.
 */
public class Characters extends AbstractCard { 

    /**
     * Constructs a Characters card with the given name.
     * 
     * @param name the name of the character
     */
    public Characters(final String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Subclasses overriding this method should return the appropriate {@link CardType}
     * for their specific card category.
     */
    @Override
    public CardType getType() {
        return CardType.CHARACTER;
    }
}
