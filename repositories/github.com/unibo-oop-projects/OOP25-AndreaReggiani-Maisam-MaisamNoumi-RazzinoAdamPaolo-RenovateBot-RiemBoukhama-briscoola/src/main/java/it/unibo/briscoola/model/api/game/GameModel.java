package it.unibo.briscoola.model.api.game;

import java.util.List;
import java.util.Optional;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;

/**
 * Interface representing the model of the Briscola game.
 * It manages the game state, the deck, the players and the rounds' progression.
 * 
 * @author Maisam Noumi
 */
public interface GameModel {

    /**
     * Starts the match by giving the initial cards to each player
     * and starting the match.
     */
    void startMatch();

    /**
     * Provides the card currently designed as the Briscola.
     * 
     * @return an Optional containg the Briscola card if present, otherwise an empty Optional.
     */
    Optional<Card> getBriscolaSeed();

    /**
     * Checks if the game is over.
     * The game ends when all players have played all their cards,
     * their hands are empty, and there are no cards left on the table.
     * 
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Allows players to draw a card from the deck after a trick.
     * If the deck is empty, the remaining players will not draw.
     * 
     * @param orderedPlayers the list of players in the order they should draw.
     */
    void drawAfterTrick(List<Player> orderedPlayers);

    /**
     * Provides the player whose in charge of the turn.
     * 
     * @return the {@link Player} currently in charge of the turn.
     */
    Player getCurrentPlayer();

    /**
     * Returns the list of players currently participating in the game.
     *
     * @return an immutable list containing all {@link Player}s in the game.
     */
    List<Player> getPlayers();

    /**
     * Checks whether the current round has concluded.
     *
     * @return true if the round is completed, false otherwise.
     */
    boolean isRoundOver();

    /**
     * Rotates the turn order so that the specified player plays first in the next round.
     * 
     * @param startingPlayer the {@link Player} who won the last round, and that will start the next.
     */
    void computeNextTurnOrder(Player startingPlayer);

    /**
     * Concludes he current round, determines the winner, assigns the won cards,
     * and updates the turn order for the next round.
     *
     * @return the {@link RoundWinner} of the concluded round.
     */
    RoundWinner endRound();

    /**
     * Executes a move by playing a specific card for the giving player.
     *
     * @param player the {@link Player} making the move.
     * @param card the {@link Card} chosen by the player.
     */
    void makeMove(Player player, Card card);

    /**
     * Returns the exact state of the table at the current moment.
     *
     * @return the current {@link RoundStateImpl}, including played cards.
     */
    RoundStateImpl getCurrentRoundState();

    /**
     * Provides the difficulty level set for the current game.
     *
     * @return the {@link Difficulty} of the game.
     */
    Difficulty getDifficulty();

    /**
     * Returns the number of cards remaining in the deck.
     *
     * @return the deck size.
     */
    int getDeckSize();
}
