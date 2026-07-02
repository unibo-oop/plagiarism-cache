package model.logic;

import java.util.List;

import model.board.GameBoard;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.utils.Boat;

/**
 * This interfaces implements some method to know the actual status of the game.
 */
public interface PlayersStatistic {

    /**
     * This method check all the players on the table and construct a string that represent their situation.
     * 
     * @param tileLine
     *                     The TileLine
     * @param boat
     *                     The boat.
     * @param pCQ
     *                     Player circular queue.
     * @return A list of string that represent all the players current treasure / direction / score / position.
     */
    List<String> getPlayersSituation(GameBoard tileLine, Boat<Player> boat, PlayerCircularQueue pCQ);

}
