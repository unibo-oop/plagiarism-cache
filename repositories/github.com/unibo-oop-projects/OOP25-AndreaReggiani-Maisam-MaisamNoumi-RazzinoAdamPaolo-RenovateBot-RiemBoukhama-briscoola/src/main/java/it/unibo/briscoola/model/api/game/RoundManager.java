package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import java.util.List;

/**
 * Interface that provides the method to create a round manager
 * to handle every moment of a game round.
 *
 * @author Adam Paolo Razzino
 */
public interface RoundManager {

    /**
     * Handles the setting up of the round.
     *
     * @param turnOrder the list of players sorted in the
     */
    void startRound(List<Player> turnOrder);

    /**
     * Handles the happening of a turn of a player and the chosen card.
     *
     * @param card of type {@link Card} chosen by the player
     * @param player of type {@link Player} in charge of the turn
     */
    void playTurn(Player player, Card card);

    /**
     * Method to get the player in charge of the turn.
     *
     * @return the {@link Player} in charge of the turn
     */
    Player getCurrentPlayer();

    /**
     * Boolean indicating if the round is completed or not.
     *
     * @return true if the round is completed, false otherwise.
     */
    boolean isRoundOver();

    /**
     * State of a moment in a turn.
     *
     * @return {@link RoundStateImpl} of the turn in the moment called
     */
    RoundStateImpl getRoundState();

    /**
     * Determines the winner based on the cards on the table.
     *
     * @return a {@link RoundPlay} with the winning player and the amount of points won
     *
     */
    RoundWinner determineWinner();
}
