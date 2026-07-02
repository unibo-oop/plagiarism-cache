package model.players;
import java.util.List;

import controller.PlayerColor;
import model.board.Tile;
import model.utils.Direction;

/**
 * This interface represent the conctract of the player class.
 */

public interface Player {

    /**
     * This method change the direction of player from "Deep" (end of tile Line) to "To_Boat" (the start of tile Line).
     */
    void changeDirectionToBoat();

    /**
     * This method set the direction of player ad "Deep" value.
     */
    void setDirectionToDeep();

    /**
     * This method is called to get the treasures carried by the player.
     * 
     * @return the playerTreasures.
     */

    List<Tile> getPlayerTreasures();

    /**
     * This method permit to pick up a treasure tile and add it to Treasure of player.
     * 
     * @param treasureTile
     *                         the treasure tile to pick up.
     * @return true if can pick up, false if can't pick up.
     */
    boolean pickUpTreasure(Tile treasureTile);

    /**
     * This allow to choose witch tile the player want to release.
     * 
     * @param index
     *                  This is the index in the list of treasure carried by player.
     * @return the tile to release.
     */
    Tile chooseTileToRelease(int index);

    /**
     * This method return the direction of the player.
     * 
     * @return a Direction rapresenting direction of the player.
     */
    Direction getDirection();

    /**
     * This method return the name of the player.
     * 
     * @return a string that represent the name of the player.
     */
    String getPlayerName();

    /**
     * This method return the playerColor of the player.
     * 
     * @return a playerColor rapresenting the playerColor
     */
    PlayerColor getPlayerColor();

    /**
     * This method return the score of the player.
     * 
     * @return an Integer that represent the total amount score of the player.
     */
    Integer getPlayerScore();

    /**
     * This method clear the treasure carried list of the player.
     */
    void resetTreasureCarried();

    /**
     * This method allow to set the player score.
     * 
     * @param value
     *                  Integer value of player score
     */
    void setPlayerScore(Integer value);

}
