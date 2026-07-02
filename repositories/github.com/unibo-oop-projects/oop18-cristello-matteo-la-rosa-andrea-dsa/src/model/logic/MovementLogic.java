package model.logic;

import model.board.GameBoard;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.utils.Boat;

/**
 * This is the interface that menage the movement logic on the table, and handle the interaction between players and
 * table.
 */
public interface MovementLogic {
    /**
     * This method return true if the player has some space to move between his position and the end of the tile line
     * (the seafloor).
     * 
     * @param tileLine
     *                     The tileLine
     * @param position
     *                     The player in turn position
     * @return a boolean value that validate if there is an empty space between the player tile and the end of the tile
     *         line
     */

    Boolean canIGoDeepFromHere(GameBoard tileLine, Integer position);

    /**
     *  This method return true if the player has his position and the boat.
     * 
     * @param tileLine
     *                     The tileline
     * @param position
     *                     The player in turn position
     * @return a boolean value that validate if there is an empty space between the player tile and the boat
     */

    Boolean isThereSomeSpaceFromMeToBoat(GameBoard tileLine, Integer position);

    /**
     * This method remove all the player from the tiles line and put it on the boat class.
     * 
     * @param tileLine
     *                     The tileline.
     * @param boat
     *                     The boat(a simply array of players)
     */

    void moveAllPlayerToBoat(GameBoard tileLine, Boat<Player> boat);

    /**
     * 
     * This method move a player from a tile to another one.
     * 
     * @param tileLine
     *                       The tileline used
     * @param player
     *                       The player in turn
     * @param boat
     *                       The boat(a simply array of players)
     * @param dicesValue
     *                       DicesValue subtracted from treasure already carried by the player)
     * @param pCQ
     *                       (playerCircularQueue)
     */

    void movePlayer(GameBoard tileLine, Player player, Integer dicesValue,
            PlayerCircularQueue pCQ, Boat<Player> boat);

}
