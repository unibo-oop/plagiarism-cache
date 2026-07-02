package model.board;


import java.util.List;
import java.util.Map;

import model.players.Player;
import model.utils.TilePlayerPair;

/**
 * This interface defines the methods that must return the logic of the board of DSA.
 */

public interface GameBoard {
    /**
     * Creation of the Board (made of tiles).
     * 
     * @param numbers
     *                    a list of integers to put as value under Tile of tileline
     */
    void createBoard(List<Integer> numbers);

    /**
     * Called from a turn of game to another turn of game to recreate the board.
     * 
     * @param linkedHash
     *                       a linkedHashMap used like a tileLine where the integer Key is the position on tileline and
     *                       the tilePlayerPair represent the tile with eventually a player on it.
     */

    void recreateBoard(Map<Integer, TilePlayerPair> linkedHash);

    /**
     * This method return an integer equal to the key of position of the player.
     * 
     * @param player
     *                   The player of whom we want to know the key value
     * @return The key index of the player choose
     */
    Integer getPlayerIndex(Player player);

    /**
     * Called to replace an empty tile with a treasure tile.
     * 
     * @param index
     *                  Of the Tile to Remove
     * @param tTile
     *                  Tile to put at the index
     */
    void replaceTile(int index, Tile tTile);

    /**
     * Called to return the board of the game.
     * 
     * @return A LinkedHashMap that is the model rapresentation of the board TileLine
     */
    Map<Integer, TilePlayerPair> getTilesLine();

    /**
     * Called to know if the player is on the board.
     * 
     * @param player
     *                   the player to check if is on board.
     * @return true if player is on board, false elsewhere.
     */
    boolean isPlayerOnBoard(Player player);

    /**
     * Take the tile under the player and give to it, after that substitute the tile with an emptyTile.
     * 
     * @param player
     *                   that should pick up the treasure under him
     * @return the tile to give to the player
     */
    Tile giveAndSubstituteTreasureTile(Player player);


}
