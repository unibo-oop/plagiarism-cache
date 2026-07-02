package it.unibo.briscoola.model.impl.player.cpu.strategies;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Strategy of the CPU for choosing the card to be played in Hard Difficulty
 * Points to use the best suited card based on the cards present on the table.
 *
 * @author Adam Paolo Razzino
 */
public class HardStrategy implements PlayStrategy {

    private static final int BRISCOLA_POWER = 2000;
    private static final int LEAD_POWER = 1000;
    private static final int FIRST_INDEX = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public int cardIndex(final List<Card> hand, final RoundStateImpl state) {
        if (hand == null || hand.isEmpty()) {
            throw new IllegalStateException("Card requested when hand is empty");
        }

        //Simplifies the handling of the state attributes
        final CardSeed briscola = state.briscola();
        final List<RoundPlay> table = state.playedCards();

        final java.util.function.Function<Card, Integer> rank = c -> {
            //Returns an over the top rank because the briscola beats all but the cards of the same seed
            if (c.getCardSeed() == briscola) {
                return BRISCOLA_POWER + c.getCardPower();
            }
            final Optional<CardSeed> lead = state.leadSeed();
            //The leadSeed if the briscola is not present, holds the highest rank
            if (lead.isPresent() && c.getCardSeed() == lead.get()) {
                return LEAD_POWER + c.getCardPower();
            }
            return c.getCardPower();
        };

        //If it is the first player of round the cpu tries to play the best card possible
        if (table.isEmpty()) {
            return hand.stream()
                    .filter(c -> c.getCardSeed() != briscola)
                    .max(Comparator.comparingInt(Card::getCardPower))
                    .or(() -> hand.stream().max(Comparator.comparingInt(Card::getCardPower)))
                    .map(hand::indexOf)
                    .orElse(FIRST_INDEX);
        }
        //If the table isn't empty
        //Selects the card that is currently winning on the table
        final Optional<Card> currentWinner = table.stream()
                .map(RoundPlay::card)
                .max(Comparator.comparingInt(rank::apply));
        //Check if there is a briscola on the table
        final boolean briscolaOnTable = table.stream()
                .anyMatch(rp -> rp.card().getCardSeed() == briscola);
        //If there's a briscola on the table
        final int missingCardIndex = -1;
        if (briscolaOnTable) {
            //Selects the highest rank of the played briscolas
            final int highestBrPower = table.stream()
                    .filter(rp -> rp.card().getCardSeed() == briscola)
                    .mapToInt(rp -> rp.card().getCardPower())
                    .max()
                    .orElse(missingCardIndex);
            //Selects, if exists, a briscola card in hand stronger than the one played
            final Optional<Card> winningBr = hand.stream()
                    .filter(c -> c.getCardSeed() == briscola && c.getCardPower() > highestBrPower)
                    .min(Comparator.comparingInt(Card::getCardPower)); // weakest winning trump
            //Returns, if exists, the winning briscola in the hand, the card with minimum power otherwise
            return winningBr.map(hand::indexOf).orElseGet(() -> hand.stream()
                    .min(Comparator.comparingInt(Card::getCardPoints))
                    .map(hand::indexOf)
                    .orElse(FIRST_INDEX));

        } else {
            final Card current = currentWinner.orElseThrow();
            final CardSeed leadSeed = current.getCardSeed();
            final int highestLeadPower = table.stream()
                    .filter(rp -> rp.card().getCardSeed() == leadSeed)
                    .mapToInt(rp -> rp.card().getCardPower())
                    .max().orElse(missingCardIndex);

            final Optional<Card> beatLead = hand.stream()
                    .filter(c -> c.getCardSeed() == leadSeed && c.getCardPower() > highestLeadPower)
                    .min(Comparator.comparingInt(Card::getCardPower));

            if (beatLead.isPresent()) {
                return hand.indexOf(beatLead.get());
            }

            final Optional<Card> useTrump = hand.stream()
                    .filter(c -> c.getCardSeed() == briscola)
                    .min(Comparator.comparingInt(Card::getCardPower));

            return useTrump.map(hand::indexOf).orElseGet(() -> hand.stream()
                    .min(Comparator.comparingInt(Card::getCardPoints))
                    .map(hand::indexOf)
                    .orElse(FIRST_INDEX));

        }
    }
}
