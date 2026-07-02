package it.unibo.briscoola.model.impl.player.cpu.strategies;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

/**
 * Strategy of the CPU for choosing the card to be played in Medium Difficulty.
 * Points to use the best fit for the card present on the table regarding the power.
 *
 * @author Adam Paolo Razzino
 */
public class MediumStrategy implements PlayStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardIndex(final List<Card> hand, final RoundStateImpl state) {
        final Optional<Card> winningCard = hand.stream()
                .filter(card -> beatsAllPlayedCards(card, state))
                .findFirst();
        if (winningCard.isPresent()) {
            return hand.indexOf(winningCard.get());
        }
        Optional<Card> worstCard = hand.stream()
                .filter(card -> card.getCardSeed() != state.briscola())
                .min(Comparator.comparingInt(Card::getCardPower));

        if (worstCard.isEmpty()) {
            worstCard = hand.stream()
                    .min(Comparator.comparingInt(Card::getCardPower));
        }

        return hand.indexOf(worstCard.orElseThrow(
                () -> new IllegalStateException("Card requested when hand is empty")));
    }

    private boolean beatsAllPlayedCards(final Card card, final RoundStateImpl state) {
        final CardSeed briscola = state.briscola();
        final CardSeed leadSeed = state.leadSeed().orElse(briscola);

        if (card.getCardSeed() != briscola && card.getCardSeed() != leadSeed) {
            return false;
        }

        return state.playedCards().stream()
                .allMatch(played -> card.getCardPower() > played.card().getCardPower());
    }
}
