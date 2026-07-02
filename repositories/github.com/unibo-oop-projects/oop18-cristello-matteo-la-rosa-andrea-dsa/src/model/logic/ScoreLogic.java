package model.logic;

import java.util.List;

import model.board.GameBoard;
import model.players.Player;
import model.utils.Boat;

/**
 * This interfaces contains all the method to manage the treasure picking and release between the class of the TileLine
 * and the players.
 */
public interface ScoreLogic {

    /**
     * With this method a selected player can pick up a TreasureTile or TreasureGroupTile.
     * 
     * @param tileLine
     *                     The tileLine.
     * @param player
     *                     The player in turn that need to pick up.
     */

    void playerPickUpTreasure(GameBoard tileLine, Player player);

    /**
     * With This method you can release a specific treasure of the player into a specific emptyTile.
     * 
     * @param tileLine
     *                     The tileLine.
     * @param player
     *                     The player in turn that need to pick up.
     * @param index
     *                     The index of the player on tileLine.
     */

    void playerReleaseTreasure(GameBoard tileLine, Player player, Integer index);

    /**
     * This method add to player score the score of their taken treasures tile (if the player is returned to the boat).
     * 
     * @param boat
     *                 The list of player that are not more on the table.
     */

    void giveScoreToPlayersOnBoat(Boat<Player> boat);

    /**
     * This method add to player score the score of their taken treasures tile (if the player is returned to the boat).
     * 
     * @param playerList
     *                 The list of player that are on table but not returned in time on the boat.
     */

    void removeAllTreasureToPlayerOnTile(List<Player> playerList);
}
