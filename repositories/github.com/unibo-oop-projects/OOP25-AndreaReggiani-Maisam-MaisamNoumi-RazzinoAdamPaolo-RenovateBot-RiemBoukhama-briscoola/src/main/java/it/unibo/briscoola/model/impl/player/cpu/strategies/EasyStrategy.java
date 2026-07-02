package it.unibo.briscoola.model.impl.player.cpu.strategies;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

/**
 * Strategy of the CPU for choosing the card to be played in Easy Difficulty.
 * Points to use the highest powered cards last.
 *
 * @author Adam Paolo Razzino
 */
public class EasyStrategy implements PlayStrategy {

    private final Logger logger = LoggerFactory.getLogger(EasyStrategy.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardIndex(final List<Card> hand, final RoundStateImpl state) {
        try {
            return hand.indexOf(hand.stream().min(Comparator.comparingInt(Card::getCardPower)).orElseThrow());
        } catch (final NoSuchElementException e) {
            logger.error("Error during the CPU playCard method -> {}", e.getMessage(), e);
            return -1;
        }
    }
}
