package model;

import com.google.common.base.Optional;

import model.cards.Card;
import model.cards.Solution;
import model.player.PlayerInfo;
import utilities.Pair;
import utilities.enumerations.RoomCard;

/**
 * This interface contains the methods that permit the AI player to take
 * decisions and play.
 */
public interface AIManager {

    /**
     * Method called after the check of a suspect. It collects data used by AI
     * players to take useful decision when it's their turn.
     * 
     * @param asker
     *            the player that formulated the suspect
     * @param suspect
     *            the suspect formulated by the player
     * @param response
     *            an optional pair containing the player and the card that
     *            disproved the suspect
     */
    void collectClue(PlayerInfo asker, Solution suspect, Optional<Pair<PlayerInfo, Card>> response);

    /**
     * Method used to choose the destination room for AI players.
     * 
     * @param steps
     *            the number of steps the player can do from his current
     *            position
     * @return the destination room (where it wants to move)
     */
    RoomCard chooseDestination(int steps);

    /**
     * Method used to make a guess for AI players.
     * 
     * @return the suspect: a triple of cards, one of each type
     */
    Solution generateSuspect();

    /**
     * This method returns true when the AI player wants to give a solution for
     * the current game (make a final accuse).
     * 
     * @return true if it has the solution of the game, false otherwise
     */
    boolean wantToAccuse();

    /**
     * Method used to give the final solution for AI players.
     * 
     * @return the computed solution: a triple of cards, one of each type
     */
    Solution giveSolution();
}