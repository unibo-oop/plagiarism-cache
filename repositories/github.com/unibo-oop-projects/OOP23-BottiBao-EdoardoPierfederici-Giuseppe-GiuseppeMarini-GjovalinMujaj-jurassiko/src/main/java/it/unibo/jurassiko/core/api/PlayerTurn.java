package it.unibo.jurassiko.core.api;

import java.util.List;

import it.unibo.jurassiko.model.player.api.Player;

/**
 * Manage the turn of the Player.
 */
public interface PlayerTurn {

    /**
     * Gets the current Player of the turn.
     * 
     * @return Player Color
     */
    Player getCurrentPlayerTurn();

    /**
     * Gets all the Players.
     * 
     * @return Set of all the Players
     */
    List<Player> getPlayers();

    /**
     * Finishes the turn of the current Player and goes to the next Player.
     */
    void goNext();

}
