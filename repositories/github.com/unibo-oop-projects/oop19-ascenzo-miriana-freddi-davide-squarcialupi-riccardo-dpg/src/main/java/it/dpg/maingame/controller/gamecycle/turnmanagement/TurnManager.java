package it.dpg.maingame.controller.gamecycle.turnmanagement;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;

import java.util.Iterator;
import java.util.List;

/**
 * PlayerManager handles the operations on the players relative to the turn order and dices, already starts in the first turn when created
 */
public interface TurnManager {

    /**
     * @return the iterator of players who have to make their turn
     */
    Iterator<PlayerController> getPlayersIterator();

    /**
     * go to the next turn making everyone play a minigame, and calculating the dices for the players of the next turn basing on the scores
     *
     * @throws IllegalStateException if no more turns have to be done
     */
    void nextTurn();

    /**
     * @return true if another turn has to be played, false otherwise
     */
    boolean hasNextTurn();

    /**
     * @return the list of players saved ordered by turn
     */
    List<PlayerController> getPlayers();
}
