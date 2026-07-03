package model;

import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import model.board.Position;
import model.cards.Card;
import model.cards.Solution;
import model.player.PlayerInfo;
import utilities.Pair;

import utilities.enumerations.RoomCard;

/**
 * It represents the interface of the model in MVC pattern. It contains the main
 * methods of the logic of the game.
 */
public interface Model {

    /**
     * Returns the current player (the one playing the current turn).
     * 
     * @return the current player.
     */
    PlayerInfo getCurrentPlayer();

    /**
     * Returns all the players of the current game.
     * 
     * @return the players of the current game.
     */
    List<PlayerInfo> getPlayers();

    /**
     * Returns a set containing the cards known to all players.
     * 
     * @return a set containing the cards known to all players
     */
    Set<Card> getCommonCards();

    /**
     * Ends a player turn and changes the current player to the next one.
     */
    void nextTurn();

    /**
     * Rolls the dices.
     * 
     * @return the result of the dices launch
     */
    int rollDices();

    /**
     * Returns a list of the reachable rooms from the current position.
     * 
     * @param steps
     *            the number of steps the player can do from his current
     *            position
     * @return a list of the reachable rooms from the current position
     */
    Set<RoomCard> getReachableRooms(int steps);

    /**
     * Moves the player into the desired room. If the player can't reach it, he
     * will be placed in the nearest cell on the board he could reach.
     * 
     * @param destinationRoom
     *            the room the player want to reach
     * @param steps
     *            the number of steps the player can do from his current
     *            position
     * @return the new position of the player
     */
    Position movePlayer(RoomCard destinationRoom, int steps);

    /**
     * Checks a suspect. If one of the other players have one of the cards
     * contained in the suspect, it returns a pair containing that card, that
     * for sure it isn't in the game solution, and the player that owns it.
     * Otherwise returns nothing.
     * 
     * @param suspect
     *            the suspect of the player: triple of cards, one of each type
     * @return a pair containing a card of another player and the player itself
     *         that can disprove the suspect
     */
    Optional<Pair<PlayerInfo, Card>> disproveSuspect(Solution suspect);

    /**
     * Checks the indicated solution. If the solution is correct (matches the
     * solution of the game) the player wins, otherwise the player is out.
     * 
     * @param possibleSolution
     *            the proposed solution: a triple of cards, one of each type
     * @return true if the solution is correct, false otherwise
     */
    boolean checkSolution(Solution possibleSolution);

    /**
     * Removes a player from the current game. This method is called in case the
     * player lost the game or decided to retire.
     * 
     * @param player
     *            the player to be removed
     */
    void removePlayer(PlayerInfo player);

    /**
     * Saves the game.
     */
    void saveGame();

    /**
     * Quits the game.
     */
    void endGame();

    /**
     * Returns true if the game is over, false otherwise.
     * 
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Returns the AIManager.
     * 
     * @return the AIManager
     */
    AIManager getAIManager();
}