package model;

import java.util.Optional;

import model.player.Player;

/**
 * commands regarding the turn management.
 */
public interface TurnManagementCommands {
    /**
     * Initiate a new turn changing the current player.
     */
    void nextTurn();

    /**
     * @return the winner player if any
     */
    Optional<Player> getWinnerPlayer();

    // ------------------------------------------------------------------------------

    /**
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * @return a string representation of the player
     */
    String getPlayerInfo();
}
