package it.unibo.monoopoly.model.deck.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import it.unibo.monoopoly.common.Message;
import it.unibo.monoopoly.model.deck.api.Card;

/**
 * the implementations of {@link Card}.
 */
@JsonTypeName("Card")
public class CardImpl implements Card {
    private final String effect;
    private final Message message;

    /**
     * the constructor of the class.
     * @param effect the text of the card's effect
     * @param message the {@link Message} to send
     */
    public CardImpl(
        @JsonProperty("effect")final String effect, 
        @JsonProperty("message")final Message message
    ) {
        this.effect = effect;
        this.message = message;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Message getMessage() {
        return this.message;
    }
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getEffectText() {
        return this.effect;
    }
}
