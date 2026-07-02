package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;

/**
 * Record class that pairs the {@link Player} with the
 * {@link Card} played.
 *
 * @author Adam Paolo Razzino
 *
 * @param player {@link Player} owner of the card
 * @param card {@link Card} played by the player
 *
 */
public record RoundPlay(Player player, Card card) {

    /**
     * Constructor that keeps the structure of the project and encapsulation safe.
     *
     * @param player {@link Player} owner of the card
     * @param card {@link Card} played by the player
     */
    public RoundPlay(final Player player, final Card card) {
        this.player = player.copy();
        this.card = card;
    }

    @Override
    public Player player() {
        return this.player.copy();
    }

}
