package it.unibo.monoopoly.model.deck.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.model.deck.impl.CardImpl;

/**
 * The interface that implement a Chance Card,
 * composed of a textual effect and a {@link Message} that contains the type of card and
 * a piece of data.
 */
@JsonDeserialize(as = CardImpl.class)
public interface Card {
    /**
     * Gets the {@link Message} representing the action of the {@link Card}.
     * 
     * @return the {@link Message} to interpret represents the action of the
     * {@link Card}
     */
    Message getMessage();

    /**
     * Gets the text of the card.
     * 
     * @return the text of the card
     */
    String getEffectText();
}
