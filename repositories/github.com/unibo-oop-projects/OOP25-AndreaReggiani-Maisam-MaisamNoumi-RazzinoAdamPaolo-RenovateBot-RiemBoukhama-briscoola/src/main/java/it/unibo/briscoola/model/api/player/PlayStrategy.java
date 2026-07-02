package it.unibo.briscoola.model.api.player;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

import java.util.List;

/**
 * Interface to handle the different CPU difficulties strategies.
 *
 * @author Adam Paolo Razzino
 */
@FunctionalInterface
public interface PlayStrategy {

    /**
     * Returns the card to be played by the CPU.
     *
     * @param hand cards in hand of the CPU
     * @param state state of the cards on the table
     * @return the card to be played
     */
    int cardIndex(List<Card> hand, RoundStateImpl state);
}
