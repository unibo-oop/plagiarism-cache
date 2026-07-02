package it.unibo.briscoola.model.impl.game;

import java.util.List;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;

/**
 * Record Class that keeps the information of the {@link Player}
 * and the cards won during a round.
 *
 * @author Adam Paolo Razzino
 *
 * @param player {@link Player} that won the Round
 * @param wonCards {@link List} of {@link Card}s won from the player during the round
 */
public record RoundWinner(Player player, List<Card> wonCards) {

    /**
     * Constructor of the Record to keep the encapsulation and the
     * safe instancing of elements.
     *
     * @param player {@link Player} that will be copied in the class
     * @param wonCards {@link List} of {@link Card}s to be copied in the class
     */
    public RoundWinner(final Player player, final List<Card> wonCards) {
        this.player = player.copy();
        this.wonCards = List.copyOf(wonCards); 
    }

    @Override
    public Player player() {
        return this.player.copy();
    }

    @Override
    public List<Card> wonCards() {
        return List.copyOf(this.wonCards);
    }
}
