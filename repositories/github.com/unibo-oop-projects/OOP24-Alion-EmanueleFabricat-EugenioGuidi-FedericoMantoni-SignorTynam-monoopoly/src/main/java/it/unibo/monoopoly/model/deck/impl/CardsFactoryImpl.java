package it.unibo.monoopoly.model.deck.impl;

import java.util.List;

import it.unibo.monoopoly.model.deck.api.Card;
import it.unibo.monoopoly.model.deck.api.CardsFactory;
import it.unibo.monoopoly.utils.api.JsonConverter;
import it.unibo.monoopoly.utils.impl.JsonConverterImpl;

/**
 * Implementation of {@link CardsFactory}.
 */
public class CardsFactoryImpl implements CardsFactory {
    private final JsonConverter<Card> deserializer;
    private static final String PATH = "json/data/cards.json";
    /**
     * constructor of the factory that sets the class.
     */
    public CardsFactoryImpl() {
        this.deserializer = new JsonConverterImpl<>(Card.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> createDeck() {
        return this.deserializer.jsonToList(PATH);
    } 
}
