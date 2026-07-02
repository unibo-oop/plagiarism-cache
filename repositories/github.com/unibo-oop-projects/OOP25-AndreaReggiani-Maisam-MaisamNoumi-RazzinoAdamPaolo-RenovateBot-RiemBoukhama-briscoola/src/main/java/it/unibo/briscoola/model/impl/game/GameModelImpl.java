package it.unibo.briscoola.model.impl.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;

/**
 * Implementation of {@link GameModel} interface.
 * This class handles the game flow (including the deck management, the player interaction and the round state transitions).
 * 
 * @author Maisam Noumi
 */
public class GameModelImpl implements GameModel {

    private final Deck<Card> deck;
    private final List<Player> players;
    private Card briscolaCard;
    private final RoundManager roundManager;
    private final Difficulty difficulty;

    /**
     * Constructs a new {@code GameModelImpl} with the specified players and deck.
     * 
     * @param players the list of players partecipating in the match.
     * @param deck the game deck to be used for the match.
     * @param difficulty the difficulty level of the game.
     */
    public GameModelImpl(final List<Player> players, final Deck<Card> deck, final Difficulty difficulty) {
        this.players = new ArrayList<>(players);
        this.deck = deck;
        this.init();
        this.roundManager = new RoundManagerImpl(briscolaCard.getCardSeed());
        this.difficulty = difficulty;
    }

    /*
     * Picks the first card from the deck as the Briscola,
     * determining the dominant seed for the match.
     */
    private void assignBriscola() {
        this.briscolaCard = this.deck.getBriscolaSeed().orElseThrow();
    }

    /*
     * Deals three initial cards to each player from the deck.
     */
    private void dealInitialCards() {
        for (int i = 0; i < 3; i++) {
            for (final Player p : players) {
                p.receiveCard(deck.draw().orElseThrow());
            }
        }
    }

    /**
     * Initializes the table state by assigning the Briscola card
     * and dealing the initial hand to the players.
     */
    private void init() {
        this.assignBriscola();
        this.dealInitialCards();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void startMatch() {

        this.roundManager.startRound(this.players);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Optional<Card> getBriscolaSeed() {
        return Optional.ofNullable(this.briscolaCard);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void drawAfterTrick(final List<Player> orderedPlayers) {
        for (final Player player: orderedPlayers) {
            final Optional<Card> card = this.deck.draw();
            card.ifPresent(player::receiveCard);
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {

        if (!this.roundManager.getRoundState().playedCards().isEmpty()) {
            return false;
        }

        for (final Player p : this.players) {
            if (!p.getHand().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.roundManager.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoundOver() {
        return this.roundManager.isRoundOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeNextTurnOrder(final Player startingPlayer) {
        final int index = this.players.indexOf(startingPlayer);
        Collections.rotate(this.players, index * (-1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoundWinner endRound() {
        final RoundWinner winner = this.roundManager.determineWinner();

        final Player actualWinner = this.players.stream()
                .filter(p -> p.getId() == winner.player().getId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No winner could be determined"));

        for (final Card wonCard : winner.wonCards()) {
            actualWinner.addtoPile(wonCard);
        }

        computeNextTurnOrder(actualWinner);
        this.drawAfterTrick(this.players);

        if (!this.isGameOver()) {
            this.roundManager.startRound(this.players);
        }
        return winner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void makeMove(final Player player, final Card card) {
    this.roundManager.playTurn(player, card);
    player.removeCard(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoundStateImpl getCurrentRoundState() {
        return this.roundManager.getRoundState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeckSize() {
        return this.deck.getCurrentSize(); 
    }
}
