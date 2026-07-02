package model.logic;

import java.util.List;

import model.board.GameBoard;
import model.players.Player;

/**
 * Interface that menage the possible movement and action a player can do at a certain point.
 */

public interface PlayerOptions {

    /**
     * Called know what available actions are possible for the player at the start of his turn.
     * 
     * @return a list of String
     */
    /**
     * @param player
     *                          The player in turn
     * @param tileLine
     *                          The tileLine
     * @param movementLogic
     *                          The manager of movement logic
     * @return a list of string that represent the direction the player can move.
     */
    List<String> whereCanIMove(Player player, GameBoard tileLine, MovementLogic movementLogic);

    /**
     * Called know what available actions are possible for the player in turn after the movement.
     * 
     * @return a list of String
     */

    /**
     * @param player
     *                     The player in turn
     * @param tileLine
     *                     The tileLine
     * @return a list of string that represent the possible action that the player in turn can do.
     */
    List<String> whatICanDoAfterMovement(Player player, GameBoard tileLine);


}
