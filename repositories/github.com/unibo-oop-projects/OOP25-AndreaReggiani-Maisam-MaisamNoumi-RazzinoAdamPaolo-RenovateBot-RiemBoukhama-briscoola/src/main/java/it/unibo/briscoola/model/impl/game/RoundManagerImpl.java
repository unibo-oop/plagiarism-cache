package it.unibo.briscoola.model.impl.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Class that handles the table for each game and has method to
 * play a round and check the state of the game.
 * Takes in input the {@link List} of {@link Player} for the round and
 * handles each part of the turn until the determination of the
 * {@link RoundWinner} after which clears the table.
 *
 * @author Adam Paolo Razzino
 */
public class RoundManagerImpl implements RoundManager {

    private final List<RoundPlay> table;
    private final CardSeed briscola;
    private List<Player> playersList;
    private CardSeed leadSeed;
    private int currentPlayerIndex;

    /**
     * Constructor of the RoundManager that creates a table with the
     * {@link CardSeed} as briscola.
     *
     * @param briscola {@link CardSeed} of the briscola card
     */
    public RoundManagerImpl(final CardSeed briscola) {
        this.table = new ArrayList<>();
        this.briscola = briscola;
        this.playersList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRound(final List<Player> turnOrder) {
        if (turnOrder == null || turnOrder.isEmpty()) {
            throw new IllegalArgumentException("List of players cannot be null");
        }
        this.playersList = List.copyOf(turnOrder);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void playTurn(final Player player, final Card card) {
        if (!this.getCurrentPlayer().equals(player)) {
            throw new IllegalArgumentException("It's not this player's turn");
        }
        if (!player.getHand().contains(card)) {
            throw new IllegalArgumentException("Card is not in the hand of this player");
        }
        if (this.table.isEmpty()) {
            this.leadSeed = card.getCardSeed();
        }
        this.table.add(new RoundPlay(player, card));

        this.currentPlayerIndex++;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.playersList.get(currentPlayerIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoundOver() {
        return currentPlayerIndex >= playersList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoundStateImpl getRoundState() {
        return new RoundStateImpl(this.table, this.briscola, Optional.ofNullable(this.leadSeed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoundWinner determineWinner() {
        if (this.table.isEmpty() || this.leadSeed == null) {
            throw new IllegalStateException();
        }
        final RoundPlay winningEntry;

        if (this.table.stream().anyMatch(a -> a.card().getCardSeed() == this.briscola)) {
            winningEntry = this.table.stream()
                    .filter(a -> a.card().getCardSeed() == this.briscola)
                    .max(Comparator.comparingInt(e -> e.card().getCardPower()))
                    .orElseThrow(() -> new IllegalStateException("No winner could be determined"));
        } else {
            winningEntry = this.table.stream()
                    .filter(a -> a.card().getCardSeed() == this.leadSeed)
                    .max(Comparator.comparingInt(e -> e.card().getCardPower()))
                    .orElseThrow(() -> new IllegalStateException("No winner could be determined"));
        }

        final List<Card> wonCards = this.table.stream().map(RoundPlay::card).toList();
        this.roundClear();

        return new RoundWinner(winningEntry.player(), wonCards);
    }

    private void roundClear() {
        this.table.clear();
        this.leadSeed = null;
        this.currentPlayerIndex = 0;
    }
}

