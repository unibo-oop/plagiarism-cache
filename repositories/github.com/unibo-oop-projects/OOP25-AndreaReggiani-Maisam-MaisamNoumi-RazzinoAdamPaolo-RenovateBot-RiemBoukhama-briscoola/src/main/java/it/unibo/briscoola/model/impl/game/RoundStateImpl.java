package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Record that holds the information of the state of the table
 * at the moment it gets instanced.
 *
 * @author Adam Paolo Razzino
 *
 * @param playedCards {@link List} of {@link RoundPlay} pairing each played card with the player
 * @param briscola {@link CardSeed} of the Briscola card
 * @param leadSeed {@link Optional} holding the {@link CardSeed} of the first card played if present, empty otherwise
 *
 */
public record RoundStateImpl(List<RoundPlay> playedCards, CardSeed briscola, Optional<CardSeed> leadSeed) {

    /**
     * Compact constructor to defend against external mutation.
     */
    public RoundStateImpl {
        playedCards = List.copyOf(playedCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoundPlay> playedCards() {
        return new ArrayList<>(playedCards);
    }
}
