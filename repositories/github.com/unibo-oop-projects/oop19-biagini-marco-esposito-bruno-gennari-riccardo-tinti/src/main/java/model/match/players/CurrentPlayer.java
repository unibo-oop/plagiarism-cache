package model.match.players;

import java.util.Optional;

import model.enums.PlayerNumber;

/**
 *  This interface models an object that keeps track of the current player.
 */
public interface CurrentPlayer {

    /**
     * @return the current player
     */
    Optional<PlayerNumber> getCurrentPlayer();

    /**
     * @param playerNumber - the new current player
     */
    void setCurrentPlayer(PlayerNumber playerNumber);

    /**
     * sets the next player as current.
     */
    void nextPlayer();

}
