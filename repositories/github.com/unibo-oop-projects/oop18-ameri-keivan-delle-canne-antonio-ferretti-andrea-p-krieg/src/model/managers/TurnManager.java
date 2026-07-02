package model.managers;

import java.util.Optional;

import model.map.ObservableGameMap;
import model.player.Player;

/**
 * Handle the turns of the match.
 */
public interface TurnManager {

    /**
     * Initiate a new turn for the next player.
     */
    void nextTurn();

    /**
     * @param map the map to be passed to the objective's checks
     * 
     * @return the winner player if it has win
     */
    Optional<Player> getWinner(ObservableGameMap map);

    /**
     * @return the player actually going through his turn
     */
    Player getTurnPlayer();

    /**
     * @param player the player that has lost
     */
    void removePlayer(Player player);

}
